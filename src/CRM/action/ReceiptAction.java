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
import CRM.entity.Receipt;
import CRM.entity.User;
import CRM.service.ReceiptService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReceiptAction extends ActionSupport implements ModelDriven<Receipt> {
	private Receipt form = new Receipt();
	public Receipt getModel() {
		return form;
	}
	
	
	//属性驱动
	//pageSize
	private int rows;
	//currPage
	private int page;
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setPage(int page) {
		this.page = page;
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
	private ReceiptService receiptService;
	public void setReceiptService(ReceiptService receiptService) {
		this.receiptService = receiptService;
	}
	
	
	//分页加载
	public String loadByPage() throws IOException, ParseException{
		//分页条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Receipt.class);
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				criteria.add(Restrictions.or(
						Restrictions.like("title",queryCriteria.getKeywords(),MatchMode.ANYWHERE),
						Restrictions.like("order_sn",queryCriteria.getKeywords(),MatchMode.ANYWHERE)));
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
		EasyUIDatagrid datagrid = receiptService.loadByPage(criteria,rows,page);
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


	//新增收款记录
	public String addReceipt() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		receiptService.addReceipt(user,form,user.getAccounts());
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	}
}
