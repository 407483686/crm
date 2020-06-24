package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import CRM.entity.AuthGroupAccess;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Extend;
import CRM.entity.QueryCriteria;
import CRM.entity.Staff;
import CRM.entity.User;
import CRM.service.StaffService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class StaffAction extends ActionSupport implements ModelDriven<Staff> {
	//模型驱动
	private Staff form = new Staff();
	public Staff getModel() {
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

	//删除员工档案的id串
	private String result;
	
	//入职时间,需要用string接收然后利用工具类转化后再set入form的属性
	private String entry_date_string;
	//离职时间，需要用string接收然后利用工具类转化后再set入form的属性
	private String dimission_date_string;
	//专业，属于拓展内容，需要手动set入extend的属性
	private String major;
	//健康,属于拓展内容，需要手动set入extend的属性
	private String heath;
	//户口,属于拓展内容，需要手动set入extend的属性
	private String residence;
	//毕业时间，属于拓展内容，需要用string接收然后利用工具类转化后再set入extend的属性
	private String graduation_time_string;
	//户口所在地,属于拓展内容，需要手动set入extend的属性
	private String registered_permanent_residence;
	//毕业院校,属于拓展内容，需要手动set入extend的属性
	private String school;
	//备注，属于拓展内容，需要手动set入extend的属性
	private String intro;
	//详情
	private String details;
	
	//选择职位后会把职位对应的权限组id传过来，这是页面写死的，后面在心间员工档案对应的access对象时，这个可以赋值
	private String group_id;
	
	
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setEntry_date_string(String entry_date_string) {
		this.entry_date_string = entry_date_string;
	}
	public void setDimission_date_string(String dimission_date_string) {
		this.dimission_date_string = dimission_date_string;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setHeath(String heath) {
		this.heath = heath;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public void setGraduation_time_string(String graduation_time_string) {
		this.graduation_time_string = graduation_time_string;
	}
	public void setRegistered_permanent_residence(
			String registered_permanent_residence) {
		this.registered_permanent_residence = registered_permanent_residence;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public void setIntro(String intro) {
		this.intro = intro;
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
	private StaffService staffService;
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	//分页查询部门,有可能带有条件
	public String loadByPage() throws IOException, ParseException{
		DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
		
		
		//设置条件
		if(queryCriteria != null){
			//员工档案的关键字可能有姓名、或者员工编号、或者电话
			//我们要把模糊查询设置为OR，即关键字的三种情况模糊查询时并列的
			if(!queryCriteria.getKeywords().equals("")){
				//添加关键字模糊查询
				criteria.add(Restrictions.or(
						Restrictions.like("name",queryCriteria.getKeywords(),MatchMode.ANYWHERE),
						Restrictions.like("number",queryCriteria.getKeywords(),MatchMode.ANYWHERE),
						Restrictions.like("tel",queryCriteria.getKeywords(),MatchMode.ANYWHERE)
				));
						
			}
			//判断是否有职位
			if(!queryCriteria.getPost().equals("")){
				criteria.add(Restrictions.eq("post",queryCriteria.getPost()));
			}
			
			//判断是否有民族
			if(!queryCriteria.getNation().equals("")){
				criteria.add(Restrictions.eq("nation",queryCriteria.getNation()));
			}
			
			//判断是否有婚姻 
			if(!queryCriteria.getMarital_status().equals("")){
				criteria.add(Restrictions.eq("marital_status",queryCriteria.getMarital_status()));
			}
			
			
			//判断是否有身份证
			if(!queryCriteria.getId_card().equals("")){
				criteria.add(Restrictions.eq("id_card",queryCriteria.getId_card()));
			}
			
			
			//判断是否有性别
			if(!queryCriteria.getGender().equals("")){
				criteria.add(Restrictions.eq("gender",queryCriteria.getGender().charAt(0)));
			}
			
			//判断是否有入职状态
			if(!queryCriteria.getEntry_status().equals("")){
				criteria.add(Restrictions.eq("entry_status",queryCriteria.getEntry_status()));
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
		EasyUIDatagrid datagrid = staffService.loadByPage(criteria,rows,page);
		if(datagrid.getRows() == null){
			ServletActionContext.getResponse().getWriter().print("{\"total\":0,\"rows\":[]}");
		}else{
			//创建Gson对象，设置输出的日期格式和忽略拓展对象
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String data = gson.toJson(datagrid);
			
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(data);
		}
		
		return NONE;
	}

	//添加员工档案和拓展
	public String addStaff() throws IOException, ParseException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		/*
		 * 一对一共享主键，在级联保存的时候，虽然不用两边都save，
		 * 但是两边都要set属性，不然会报id空异常
		 * */
		Extend extend = new Extend();
		AuthGroupAccess aa = new AuthGroupAccess();
		aa.setGroupId(Integer.parseInt(group_id));
		form.setAuthGroupAccess(aa);
		aa.setStaff(form);
		
		//入职时间
		if(!entry_date_string.equals("")){
			Date entry_date = TimeFormat.changeToDate(entry_date_string);
			form.setEntry_date(entry_date);
		}
		//离职时间
		if(!dimission_date_string.equals("")){
			Date dimission_date = TimeFormat.changeToDate(dimission_date_string);
			form.setDimission_date(dimission_date);
		}
		//专业
		if(!major.equals("")){
			extend.setMajor(major);
		}
		//健康状况
		if(!heath.equals("")){
			extend.setHeath(heath);
		}
		//户口
		if(!residence.equals("")){
			extend.setResidence(residence);
		}
		
		//毕业时间
		if(!graduation_time_string.equals("")){
			Date graduation_time = TimeFormat.changeToDate(graduation_time_string);
			extend.setGraduation_time(graduation_time);
		}
		//户口所在地
		if(!registered_permanent_residence.equals("")){
			extend.setRegistered_permanent_residence(registered_permanent_residence);
		}
		//毕业院校
		if(!school.equals("")){
			extend.setSchool(school);
		}
		//个人简介
		if(!intro.equals("")){
			extend.setIntro(intro);
		}
		//详情
		if(!details.equals("")){
			extend.setDetails(details);
		}
		
		//添加
		if(staffService.addStaff(user,form,extend)){
			ServletActionContext.getResponse().getWriter().print(1);
		}else{
			ServletActionContext.getResponse().getWriter().print(0);
		}
		
		return NONE;
	}

	//删除员工档案和拓展
	public String remove() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String[] ids = result.split("丨");
		int index = staffService.removeStaff(user,ids);
		
		ServletActionContext.getResponse().getWriter().print(index);
		
		return NONE;
	}
	
	//根绝员工id加载员工档案和拓展资料
	public String getOne() throws IOException{
		Staff staff = staffService.getOne(form.getId());
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("MM/dd/yyyy").create();
		
		String data = gson.toJson(staff);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		System.out.println(data);
		return NONE;
	}
	
	//修改员工档案的方法
	public String editStaff() throws ParseException, IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		Extend extend = new Extend();

		//入职时间
		if(!entry_date_string.equals("")){
			Date entry_date = TimeFormat.changeToDate(entry_date_string);
			form.setEntry_date(entry_date);
		}
		//离职时间
		if(!dimission_date_string.equals("")){
			Date dimission_date = TimeFormat.changeToDate(dimission_date_string);
			form.setDimission_date(dimission_date);
		}
		//专业
		if(!major.equals("")){
			extend.setMajor(major);
		}
		//健康状况
		if(!heath.equals("")){
			extend.setHeath(heath);
		}
		//户口
		if(!residence.equals("")){
			extend.setResidence(residence);
		}
		
		//毕业时间
		if(!graduation_time_string.equals("")){
			Date graduation_time = TimeFormat.changeToDate(graduation_time_string);
			extend.setGraduation_time(graduation_time);
		}
		//户口所在地
		if(!registered_permanent_residence.equals("")){
			extend.setRegistered_permanent_residence(registered_permanent_residence);
		}
		//毕业院校
		if(!school.equals("")){
			extend.setSchool(school);
		}
		//个人简介
		if(!intro.equals("")){
			extend.setIntro(intro);
		}
		//详情
		if(!details.equals("")){
			extend.setDetails(details);
		}
		
		//修改
		staffService.editStaff(user,form,extend,group_id);
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
	
	
	//分页查询所有未关联的员工档案,未关联即对象的user不存在
	public String getNotRelationList() throws IOException{
		DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
		
		criteria.add(Restrictions.isNull("user"));
		
		//传入每页记录数和当前页返回EasyUIDatagrid对象
		EasyUIDatagrid datagrid = staffService.loadByPage(criteria,rows,page);
		if(datagrid.getRows() == null){
			ServletActionContext.getResponse().getWriter().print("{\"total\":0,\"rows\":[]}");
		}else{
			//创建Gson对象，设置输出的日期格式和忽略拓展对象
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String data = gson.toJson(datagrid);
			
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(data);
		}
		
		return NONE;
	}
	
	
	//获取详情
	public String getDetails() throws IOException{
		Staff staff = staffService.getOne(form.getId());
		ServletActionContext.getRequest().getSession().setAttribute("staff",staff);
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
}
