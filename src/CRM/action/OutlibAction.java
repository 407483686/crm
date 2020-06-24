package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Outlib;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.OutlibService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OutlibAction extends ActionSupport implements ModelDriven<Outlib> {
	private Outlib form = new Outlib();
	public Outlib getModel() {
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
	//出库的ids
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
	private OutlibService outlibService;

	public void setOutlibService(OutlibService outlibService) {
		this.outlibService = outlibService;
	}
	

	//分页查询
	public String loadByPage() throws ParseException, IOException{
		DetachedCriteria criteria = DetachedCriteria.forClass(Outlib.class);
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				criteria.add(Restrictions.eq("order_sn",queryCriteria.getKeywords()));
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
		EasyUIDatagrid datagrid = outlibService.loadByPage(criteria,rows,page);
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


	//出库
	public String deliver() throws IOException{
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		String[] ids = result.split("丨");
		int index = outlibService.deliver(user,ids,user.getAccounts());
		ServletActionContext.getResponse().getWriter().print(index);
		
		return NONE;
	}
}
