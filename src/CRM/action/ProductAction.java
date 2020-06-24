
package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Extend;
import CRM.entity.Product;
import CRM.entity.ProductExtend;
import CRM.entity.QueryCriteria;
import CRM.entity.Staff;
import CRM.entity.User;
import CRM.service.ProductService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product> {

	//模型驱动
	Product form = new Product();
	public Product getModel() {
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
	//删除产品信息的id串
	private String result;
	
	
	//如果为true，只选择库存不为0的产品
	private Boolean kuCun;
	public void setKuCun(Boolean kuCun) {
		this.kuCun = kuCun;
	}
	
	
	//新增产品信息的详情
	private String details;
	
	//库存警报,Boolean就判断null，boolean就判断false都可以
	private Boolean alarm;
	
	public void setAlarm(Boolean alarm) {
		this.alarm = alarm;
	}
	public void setDetails(String details) {
		this.details = details;
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
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//分页查询产品信息,有可能带有条件
	public String loadByPage() throws IOException, ParseException{
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		
		//当接收到库存参数时，启用库存判断，筛选出库存不为0的产品以供销售订单的选择
		if(kuCun != null){
			criteria.add(Restrictions.ne("inventory",0));
		}
		
		//当接收到警报参数时，启用警报判断
		if(alarm != null){
			//筛选出库存量低于警报值的产品信息
			criteria.add(Restrictions.leProperty("inventory", "inventory_alarm"));
		}
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				criteria.add(Restrictions.or(Restrictions.like("name",queryCriteria.getKeywords(), MatchMode.ANYWHERE)
											,Restrictions.like("sn",queryCriteria.getKeywords(),MatchMode.ANYWHERE)));
			}
			
			if(!queryCriteria.getType().equals("")){
				criteria.add(Restrictions.eq("type",queryCriteria.getType()));
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
		EasyUIDatagrid datagrid = productService.loadByPage(criteria,rows,page);
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
	
	
	//删除
	public String remove() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String[] ids = result.split("丨");
		int index = productService.removeProduct(user,ids);
		
		ServletActionContext.getResponse().getWriter().print(index);
		
		
		return NONE;
	}

	
	//添加产品信息
	public String addProduct() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//添加
		ProductExtend productExtend = new ProductExtend();
		if(!details.equals("")){
			productExtend.setDetails(details);
		}
		
		
		if(productService.addProduct(user,form,productExtend)){
			ServletActionContext.getResponse().getWriter().print(1);
		}else{
			ServletActionContext.getResponse().getWriter().print(0);
		}
				
		return NONE;
	}
	
	
	//根据id来获取一个产品信息对象，用于修改前的表单回显
	public String getOne() throws IOException{
		Product product = productService.getOne(form.getId());
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		
		String data = gson.toJson(product);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		return NONE;
	}
	
	
	//修改产品信息
	public String editProduct() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		ProductExtend productExtend = new ProductExtend();
		productExtend.setDetails(details);
		
		//修改
		productService.editProduct(user,form,productExtend);
		ServletActionContext.getResponse().getWriter().print(1);
		
		
		return NONE;
	}
	
	
	//获取产品信息详情
	public String getDetails(){
		Product product = productService.getOne(form.getId());
		ServletActionContext.getRequest().getSession().setAttribute("product",product);
		
		return NONE;
	}

	//图标调用此方法查询数据
	public String loadAll() throws IOException{
		List<Product> list = productService.loadAll();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String data = gson.toJson(list);
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		return NONE;
	}
}
