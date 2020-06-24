package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import CRM.entity.Documentary;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.DocumentaryService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DocumentaryAction extends ActionSupport implements ModelDriven<Documentary> {
	private Documentary form = new Documentary();
	public Documentary getModel() {
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
	
	//业务层
	private DocumentaryService documentaryService;
	public void setDocumentaryService(DocumentaryService documentaryService) {
		this.documentaryService = documentaryService;
	}
	
	//为true则只筛选出进展为谈判中的跟单记录
	private Boolean tanPan;
	
	public void setTanPan(Boolean tanPan) {
		this.tanPan = tanPan;
	}


	//属性驱动
	//pageSize
	private int rows;
	//currPage
	private int page;
	
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


	//分页加载
	public String loadByPage() throws IOException, ParseException{
		//分页条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Documentary.class);
		
		
		//判断是否筛选谈判中跟单
		if(tanPan != null){
			criteria.add(Restrictions.eq("evolve","谈判中"));
		}
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				criteria.add(Restrictions.or(
						Restrictions.like("title",queryCriteria.getKeywords(),MatchMode.ANYWHERE),
						Restrictions.like("company",queryCriteria.getKeywords(),MatchMode.ANYWHERE),
						Restrictions.like("staff_name",queryCriteria.getKeywords(),MatchMode.ANYWHERE)));
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
		EasyUIDatagrid datagrid = documentaryService.loadByPage(criteria,rows,page);
		if(datagrid.getRows() == null){
			ServletActionContext.getResponse().getWriter().print("{\"total\":0,\"rows\":[]}");
		}else{
			//创建Gson对象，设置输出的日期格式和忽略拓展对象
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String data = gson.toJson(datagrid);
			
			ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().print(data);
			
			System.out.println(data);
		}
		
		return NONE;
	}


	//删除跟单记录
	public String remove() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String[] ids = result.split("丨");
		int index = documentaryService.removeDocumentary(user,ids);
		
		ServletActionContext.getResponse().getWriter().print(index);
		
		return NONE;
	}
	
	
	//修改之前回显
	public String getOne() throws IOException{
		Documentary docu = documentaryService.getOne(form.getId());
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String data = gson.toJson(docu);
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		
		
		return NONE;
	}
	
	//修改的方法
	public String editDocumentary() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		documentaryService.editDocumentary(user,form);
		ServletActionContext.getResponse().getWriter().print(1);
		
		
		return NONE;
	}


	//新增
	public String addDocumentary() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		documentaryService.addDocumentary(user,form,user.getAccounts());
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
}
