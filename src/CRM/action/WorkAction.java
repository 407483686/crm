package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.entity.Work;
import CRM.service.WorkService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class WorkAction extends ActionSupport implements ModelDriven<Work> {
	private Work form = new Work();
	public Work getModel() {
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
	
	//作废的工作计划id串
	private String result;
	
	//是否选择实行人为当前登陆员工，这是工作计划
	private Boolean ShiXing;
	
	//是否选择发起人为当前登陆员工，这是分配任务
	private Boolean FaQi;
	
	//工作计划开始时间
	private String state_date_string;
	//工作计划结束时间
	private String end_date_string;
	
	//增加工作阶段的名称
	private String stage_title;
	

	public void setShiXing(Boolean shiXing) {
		ShiXing = shiXing;
	}
	public void setFaQi(Boolean faQi) {
		FaQi = faQi;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setStage_title(String stage_title) {
		this.stage_title = stage_title;
	}
	public void setState_date_string(String state_date_string) {
		this.state_date_string = state_date_string;
	}
	public void setEnd_date_string(String end_date_string) {
		this.end_date_string = end_date_string;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
	//业务层
	private WorkService workService;
	public void setWorkService(WorkService workService) {
		this.workService = workService;
	}
	
	//查询
	public String loadByPage() throws ParseException, IOException{
		//分页查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Work.class);
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//选择实行人为当前登陆员工，这是工作计划
		if(ShiXing != null){
			criteria.add(Restrictions.eq("staff_id",user.getStaff().getId()));
		}
		
		//选择发起人为当前登陆员工，这是分配任务
		if(FaQi != null){
			criteria.add(Restrictions.eq("enter_id",user.getStaff().getId()));
		}
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				criteria.add(Restrictions.like("title",queryCriteria.getKeywords(), MatchMode.ANYWHERE));
			}
			
			if(!queryCriteria.getWork_state().equals("")){
				criteria.add(Restrictions.eq("state",queryCriteria.getWork_state()));
			}
			
			if(!queryCriteria.getWork_type().equals("")){
				criteria.add(Restrictions.eq("type",queryCriteria.getWork_type()));
			}
			
			
			if(!queryCriteria.getDateType().equals("")){
				if(!queryCriteria.getDateFrom().equals("")){
					Date ts_from = TimeFormat.changeToDate(queryCriteria.getDateFrom());
					criteria.add(Restrictions.ge(queryCriteria.getDateType(),ts_from));
				}
				if(!queryCriteria.getDateTo().equals("")){
					Date ts_to = TimeFormat.changeToDate(queryCriteria.getDateTo());
					criteria.add(Restrictions.le(queryCriteria.getDateType(),ts_to));
				}
			}
		}
		
	
		//传入每页记录数和当前页返回EasyUIDatagrid对象
		EasyUIDatagrid datagrid = workService.loadByPage(criteria,rows,page);
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


	//更新阶段之前的回显
	public String getOne() throws IOException{
		Work work = workService.getOne(form.getId());
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String data = gson.toJson(work);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		
		return NONE;
	}
	
	//更新阶段面板的数据列表
	public String getWorkStage() throws IOException{
		Work work = workService.getOne(form.getId());
		
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String data = gson.toJson(work.getWorkStages());
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		
		return NONE;
	}
	
	
	
	//添加
	public String addWork() throws IOException, ParseException{
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		workService.addWork(user,form,state_date_string,end_date_string);
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}


	//新增工作阶段
	public String addWorkStage() throws IOException{
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		workService.addWorkStage(user,form.getId(),stage_title);
		
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	}
	
	//完成工作计划
	public String finish() throws IOException{
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		workService.finish(user,form.getId());
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	}
	
	
	//作废
	public String removeWork() throws IOException{
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		String[] ids = result.split("丨");
		int index = workService.removeWork(user,ids);
		ServletActionContext.getResponse().getWriter().print(index);
		return NONE;
	}


	//添加分配工作计划
	public String addAllo() throws IOException, ParseException{
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		workService.addAllo(form,user,state_date_string,end_date_string);
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
}
