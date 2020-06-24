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
import CRM.entity.Order;
import CRM.entity.Outlib;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.OrderService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	Order form = new Order();
	public Order getModel() {
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
	//删除
	private String result;
	//用于接收订单选择的产品
	private String products;
	//用于接收关联的跟单id
	private String documentary_id;
	
	//当不为null时，表示选择未支付的订单
	private Boolean zhiFu;
	
	
	public void setZhiFu(Boolean zhiFu) {
		this.zhiFu = zhiFu;
	}
	public void setDocumentary_id(String documentary_id) {
		this.documentary_id = documentary_id;
	}
	public void setProducts(String products) {
		this.products = products;
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
	
	
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//分页查询
	public String loadByPage() throws ParseException, IOException{
		DetachedCriteria criteria = DetachedCriteria.forClass(Order.class);
		
		//当不为null时，表示选择未支付的订单
		if(zhiFu != null){
			criteria.add(Restrictions.eq("pay_state","未支付"));
		}
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				criteria.add(Restrictions.or(Restrictions.like("sn",queryCriteria.getKeywords(), MatchMode.ANYWHERE)
											,Restrictions.like("title",queryCriteria.getKeywords(),MatchMode.ANYWHERE)));
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
		EasyUIDatagrid datagrid = orderService.loadByPage(criteria,rows,page);
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

	//回显详情
	public String getDetails(){
		Order order = orderService.getOne(form.getId());
		List<Outlib> outlibList = orderService.getOutlibList(order.getSn());
		
		ServletActionContext.getRequest().getSession().setAttribute("order",order);
		ServletActionContext.getRequest().getSession().setAttribute("outlibList",outlibList);
		
		return NONE;
	}


	//新增订单
	public String addOrder() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		Gson gson = new Gson();
		//把产品json串转为对象后再传入
		products = products.replaceAll("id","product_id").replaceAll("sn","product_sn").replaceAll("name","product_name");
		List<Outlib> outlibList = gson.fromJson(products, new TypeToken<List<Outlib>>(){}.getType());  
		
		orderService.addOrder(user,form,outlibList,documentary_id);
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	}
	
	//修改前的回显
	public String getOne() throws IOException{
		Order order = orderService.getOne(form.getId());
		String data = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(order);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		
		return NONE;
	}
	
	//修改
	public String editOrder() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		orderService.updateOrder(user,form.getId(),form.getDetails());
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	}
}
