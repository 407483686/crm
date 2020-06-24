package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Post;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.StaffService;
import CRM.service.UserService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	//模型驱动
	private User form = new User();
	public User getModel() {
		return form;
	}
	
	//对象驱动
	private QueryCriteria queryCriteria;
	public QueryCriteria getQueryCriteria() {
		return queryCriteria;
	}
	public void setQueryCriteria(QueryCriteria queryCriteria) {
		this.queryCriteria = queryCriteria;
	}

	//属性驱动
	//pageSize
	private int rows;
	//currPage
	private int page;
	//删除用户的id串
	private String result;
	//关联的员工档案的id
	private String staff_id;
	//要清空userId的多个关联档案的id
	private String staff_ids;
	
	public void setStaff_ids(String staff_ids) {
		this.staff_ids = staff_ids;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	//业务层
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//分页查询部门,有可能带有条件
	public String loadByPage() throws IOException, ParseException{
		/*
		 * SSH的sessionFactory在hibernateTemplate中，那么势必就是在dao才能创建，
		 * 而普通的Criteria是sessionFactory创建的，因此就必须等到dao才能创建，而后的设置条件会把dao弄得很混乱
		 * 而现在的离线Criteria无需sessionFactory就能创建，因此在web层就直接能把条件设置好，
		 * 甚至其他代码都不用改动
		 * */
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		
		//设置条件
		if(queryCriteria != null){
			//注意：前台easyUI的表格的load方法，当textbox没写时，传过来不是null，而是""空字符串
			//先判断是否有关键字
			if(!queryCriteria.getKeywords().equals("")){
				//添加关键字模糊查询
				criteria.add(Restrictions.like("accounts",queryCriteria.getKeywords(),MatchMode.ANYWHERE));
			}
			
			//判断是否有用户状态
			if(!queryCriteria.getState().equals("")){
				criteria.add(Restrictions.eq("state",queryCriteria.getState()));
			}
			
			//判断是否有dateType，这个没有的话起始和结束都不会有
			if(!queryCriteria.getDateType().equals("")){
				if(!queryCriteria.getDateFrom().equals("")){
					//有起始时间的话
					//需要先转化格式
					Date ts_from = TimeFormat.changeToDate(queryCriteria.getDateFrom());
					criteria.add(Restrictions.ge(queryCriteria.getDateType(),ts_from));
				}
				if(!queryCriteria.getDateTo().equals("")){
					//有起始时间的话
					//需要先转化格式
					Date ts_to = TimeFormat.changeToDate(queryCriteria.getDateTo());
					criteria.add(Restrictions.le(queryCriteria.getDateType(),ts_to));
				}
			}
		}
		
		//传入每页记录数和当前页返回EasyUIDatagrid对象
		EasyUIDatagrid datagrid = userService.loadByPage(criteria,rows,page);
		if(datagrid.getRows() == null){
			//查不到数据，手动返回空内容
			ServletActionContext.getResponse().getWriter().print("{\"total\":0,\"rows\":[]}");
		}else{
			//创建Gson对象，设置输出的日期格式
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").excludeFieldsWithoutExposeAnnotation().create();
			String data = gson.toJson(datagrid);
			
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(data);
		}
		
		return NONE;
	}

	//删除用户
	public String removeUser() throws IOException{
		User userLogin = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String[] ids = result.split("丨");
		String[] staff_ids_arr = staff_ids.split("丨");
		int index = userService.removeUsersByIds(userLogin,ids,staff_ids_arr);
		ServletActionContext.getResponse().getWriter().print(index);
		return NONE;
	}

	//新增用户
	public String addUser() throws IOException{
		User userLogin = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		User user = userService.addUser(userLogin,form,staff_id);
		if(null != user){
			ServletActionContext.getResponse().getWriter().print(1);
		}else{
			ServletActionContext.getResponse().getWriter().print(0);
		}
		return NONE;
	}

	//点击右侧图标修改状态
	public String editOnlyState() throws IOException{
		User userLogin = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		userService.editUserState(userLogin,form);
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
	
	//修改用户的密码和状态或者单独修改密码
	public String edit() throws IOException{
		User userLogin = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		User user;
		//这里为什么要判断null和空字符串呢？
		//1.如果是easyUI表单提交的话，即使那一行没有填写，也会有空字符串
		//2.如果是JQuery的ajax请求的话，没有写的数据一定是null
		//凡是有判断null和空字符串的，null判断一律放在前面不然当null时调用equals就会报空指针异常
		if(form.getPassword() == null || form.getPassword().equals("")){
			//如果提交过来的表单密码为空，说明用户不想修改密码，那么此时修改状态就行
			user = userService.editUserState(userLogin,form);
		}else{
			//密码不为空就修改密码和状态
			user = userService.editUser(userLogin,form);
		}
		//修改状态返回的user不可能为空，只有密码相同会返回null
		if(null != user){
			//因为在修改之前，会给这个combogrid赋值，此时赋值的是用户对应的员工的名字，若用户没有点击更改，那么
			//其值就不会变成id，而是文字，那么我们通过判断是否是纯数字，就可以判断是否需要对用户对应的员工档案进行修改
			boolean flag = true;
			for(int i=0;i < staff_id.length();i++){
				if(!Character.isDigit(staff_id.charAt(i))){
					flag = false;
				}
			}
			
			if(flag == true){
				userService.editUserStaffNameAndUserId(userLogin,user,staff_id);
			}
			
			
			ServletActionContext.getResponse().getWriter().print(1);
		}else{
			//返回空，说明用户的密码和原来一样
			ServletActionContext.getResponse().getWriter().print(0);
		}
		return NONE;
	}
	
	//登录账号
	public String login() throws IOException{
		User user = userService.login(form);
		if(null != user){
			if(user.getState().equals("冻结")){
				//冻结状态的用户不能登录
				ServletActionContext.getResponse().getWriter().print(-1);
			}else{
				//登录成功后向session写入信息
				ServletActionContext.getRequest().getSession().setAttribute("user",user);

				//然后更新用户的登录次数、最后登录时间和最后登录ip
				String ip = ServletActionContext.getRequest().getRemoteAddr();
				userService.update(user,ip);
				
				ServletActionContext.getResponse().getWriter().print(1);
			}
		}else{
			//返回空,说明找不到用户，用户名或者密码错误
			ServletActionContext.getResponse().getWriter().print(0);
		}
		
		return NONE;
	}
	
	public String logout() throws IOException{
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
	
	//只单独修改密码，适用于再main.jsp右上角按钮弹窗的表单提交
	public String editPassword() throws IOException{
		User userLogin = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		User user = userService.editUserPassword(userLogin,form);
		if(null != user){
			//如果有user返回，说明新密码与老密码不冲突，修改成功
			ServletActionContext.getResponse().getWriter().print(1);
		}else{
			//返回空，说明用户的密码和原来一样，修改失败
			ServletActionContext.getResponse().getWriter().print(0);
		}
		
		
		return NONE;
	}
}
