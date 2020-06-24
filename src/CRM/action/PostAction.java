package CRM.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Post;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.PostService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PostAction extends ActionSupport implements ModelDriven<Post> {
	//模型驱动
	Post form = new Post();
	public Post getModel() {
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
	//删除部门的id串
	private String result;
	
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
	private PostService postService;
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	//分页查询部门,有可能带有条件
	public String loadByPage() throws IOException, ParseException{
		/*
		 * SSH的sessionFactory在hibernateTemplate中，那么势必就是在dao才能创建，
		 * 而普通的Criteria是sessionFactory创建的，因此就必须等到dao才能创建，而后的设置条件会把dao弄得很混乱
		 * 而现在的离线Criteria无需sessionFactory就能创建，因此在web层就直接能把条件设置好，
		 * 甚至其他代码都不用改动
		 * */
		DetachedCriteria criteria = DetachedCriteria.forClass(Post.class);
		
		//设置条件
		if(queryCriteria != null){
			//注意：前台easyUI的表格的load方法，当textbox没写时，传过来不是null，而是""空字符串
			//先判断是否有关键字
			if(!queryCriteria.getKeywords().equals("")){
				//添加关键字模糊查询
				criteria.add(Restrictions.like("name",queryCriteria.getKeywords(),MatchMode.ANYWHERE));
			}
			
			//判断是否有dateType，这个没有的话起始和结束都不会有
			if(!queryCriteria.getDateType().equals("")){
				if(!queryCriteria.getDateFrom().equals("")){
					//有起始时间的话
					//需要先转化格式
					Timestamp ts_from = TimeFormat.changeToTimestamp(queryCriteria.getDateFrom());
					criteria.add(Restrictions.ge(queryCriteria.getDateType(),ts_from));
				}
				if(!queryCriteria.getDateTo().equals("")){
					//有起始时间的话
					//需要先转化格式
					Timestamp ts_to = TimeFormat.changeToTimestamp(queryCriteria.getDateTo());
					criteria.add(Restrictions.le(queryCriteria.getDateType(),ts_to));
				}
			}
		}
		
		//传入每页记录数和当前页返回EasyUIDatagrid对象
		EasyUIDatagrid datagrid = postService.loadByPage(criteria,rows,page);
		if(datagrid.getRows() == null){
			/*
			 * 这里有一个困扰我一个多小时的问题：为什么搜aaa然后返回的时全部的数据？
			 * 一开始以为时分页查询出了问题，后面打开F12发现了问题
			 * 原来分页查询并没有问题，返回的total是0，但是由于dao方法中，如果查询补刀数据，返回的是null
			 * 也就是没有给EasyUIDatagrid对象设置它的rows属性，那么在解析成字符串时就不会有rows这一项
			 * 连空数组都没有，那么这样以来页面接收不到完整的参数，少了一个rows，整个datagrid就不会刷新，
			 * 造成了一种查到所有数据的假象。
			 * 
			 * 特此记录。。。
			 * 时间：2020-3-30 1:42:00
			 * */
			ServletActionContext.getResponse().getWriter().print("{\"total\":0,\"rows\":[]}");
		}else{
			//创建Gson对象，设置输出的日期格式
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String data = gson.toJson(datagrid);
			
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(data);
		}
		
		return NONE;
	}
	
	//员工档案的添加框中的列表加载这个方法
	public String loadPost() throws IOException{
		List<Post> list = postService.load();
		if(list != null){
			String data = new Gson().toJson(list);
			
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(data);
		}
		return NONE;
	}
	
	//添加部门
	public String addPost() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//后端验证是防止有人绕过前端页面
		//后端验证1：长度验证
		if(form.getName().length() <= 10 && form.getName().length() >= 2){
			Post post = postService.addPost(user,form);
			if(null != post){
				System.out.println(post.getName());
				ServletActionContext.getResponse().getWriter().print(1);
			}else{
				ServletActionContext.getResponse().getWriter().print(0);
			}
		}
		
		return NONE;
	}
	
	//修改部门
	public String editPost() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//返回一个修改的记录数
		//如果名称不重复，返回1，如果名称重复，修改失败返回0
		int index = postService.editPost(user,form);
		ServletActionContext.getResponse().getWriter().print(index);
		
		return NONE;
	}
	
	//删除部门
	public String removePost() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String[] arr = result.split("丨");
		int index = postService.removePostByIds(user,arr);
		ServletActionContext.getResponse().getWriter().print(index);
		
		return NONE;
	}
	
}


