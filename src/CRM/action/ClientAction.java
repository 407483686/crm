package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import CRM.entity.Client;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.ClientService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ClientAction extends ActionSupport implements ModelDriven<Client> {
	private Client form = new Client();
	public Client getModel() {
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
	//删除客户的id串
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
	private ClientService clientService;
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	
	public String loadByPage() throws ParseException, IOException{
		//分页查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Client.class);
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				criteria.add(Restrictions.or(Restrictions.like("company",queryCriteria.getKeywords(), MatchMode.ANYWHERE)
											,Restrictions.like("name",queryCriteria.getKeywords(),MatchMode.ANYWHERE)
											,Restrictions.like("tel",queryCriteria.getKeywords(),MatchMode.ANYWHERE)));
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
		EasyUIDatagrid datagrid = clientService.loadByPage(criteria,rows,page);
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
		Client client = clientService.getOne(form.getId());
		ServletActionContext.getRequest().getSession().setAttribute("client",client);
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
	
	//修改回显
	public String getOne() throws IOException{
		Client client = clientService.getOne(form.getId());
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String data = gson.toJson(client);
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		
		return NONE;
	}
	
	
	//新增客户
	public String addClient() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(clientService.addClient(user,form)){
			ServletActionContext.getResponse().getWriter().print(1);
		}else{
			ServletActionContext.getResponse().getWriter().print(0);
		}
		
		return NONE;
	}
	
	//修改客户
	public String editClient() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(clientService.editClient(user,form)){
			ServletActionContext.getResponse().getWriter().print(1);
		}else{
			ServletActionContext.getResponse().getWriter().print(0);
		}
		
		return NONE;
	}
	
	//删除客户
	public String remove() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String[] ids = result.split("丨");
		int index = clientService.removeClient(user,ids);
		
		ServletActionContext.getResponse().getWriter().print(index);
		
		return NONE;
	}

	//客户分析查询方法
	public String loadAll()throws IOException{
		List<Client> list = clientService.loadAll();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String data = gson.toJson(list);
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		return NONE;
	}
}
