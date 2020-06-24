package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Inlib;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.InlibService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class InlibAction extends ActionSupport implements ModelDriven<Inlib> {
	//模型驱动
	private Inlib form = new Inlib();
	public Inlib getModel() {
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
	//接收产品的id
	private String product_id;
	//这个参数为true则只查询入库方式为采购的数据
	private Boolean procure;
	
	public void setProcure(Boolean procure) {
		this.procure = procure;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setPage(int page) {
		this.page = page;
	}

	

	//业务层
	private InlibService inlibService;
	public void setInlibService(InlibService inlibService) {
		this.inlibService = inlibService;
	}
	
	
	//新增入库记录
	public String addInlib() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//设置经办人为当前登录的管理员账号
		form.setEnter(user.getAccounts());
		//设置创建时间
		form.setCreate_time(new Date());
		//product_id要到dao层中给他赋值，为什么要product对象不要String的id，因为列表需要列出产品名字,而且分页查询也比较方便
		inlibService.addInlib(user,form,product_id);
		
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
	
	//分页加载
	public String loadByPage() throws IOException, ParseException{
		//分页条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Inlib.class);
		
		if(procure != null){
			criteria.add(Restrictions.eq("mode","采购"));
		}
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				//由于多表查询出现错误，这里就先不写多表了，把产品名称和产品编号都去掉，只通过经办人来查找
				criteria.add(Restrictions.like("staff_name",queryCriteria.getKeywords(),MatchMode.ANYWHERE));
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
		EasyUIDatagrid datagrid = inlibService.loadByPage(criteria,rows,page);
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
	
	
	//加载详情
	public String getDetails(){
		Inlib inlib = inlibService.getOne(form.getId());
		ServletActionContext.getRequest().getSession().setAttribute("inlib",inlib);
		return NONE;
	}
}
