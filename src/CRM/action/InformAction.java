package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import CRM.entity.Client;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Inform;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.InformService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class InformAction extends ActionSupport implements ModelDriven<Inform> {
	private Inform form = new Inform();
	public Inform getModel() {
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
	//删除的通知id串
	private String result;
	
	public void setResult(String result) {
		this.result = result;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	//业务层
	private InformService informService;
	public void setInformService(InformService informService) {
		this.informService = informService;
	}
	
	
	public String loadByPage() throws ParseException, IOException{
		//分页查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Inform.class);
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				criteria.add(Restrictions.or(Restrictions.like("title",queryCriteria.getKeywords(), MatchMode.ANYWHERE)
											,Restrictions.like("staff_name",queryCriteria.getKeywords(),MatchMode.ANYWHERE)));
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
		EasyUIDatagrid datagrid = informService.loadByPage(criteria,rows,page);
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
	
	
	//详情
	public String getDetails() throws IOException{
		Inform inform = informService.getOne(form.getId());
		ServletActionContext.getRequest().getSession().setAttribute("inform",inform);
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
	
	//新增
	public String addInform() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		informService.addInform(user,form);
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	}
	
	//修改前的回显
	public String getOne() throws IOException{
		Inform inform = informService.getOne(form.getId());
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String data = gson.toJson(inform);
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		return NONE;
	}
	
	//修改
	public String editInform() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		informService.editInform(user,form);
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	}
	
	//删除
	public String remove() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		int index = informService.removeInform(user,result);
		
		ServletActionContext.getResponse().getWriter().print(index);
		return NONE;
	}

}
