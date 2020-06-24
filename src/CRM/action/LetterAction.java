package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Letter;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.LetterService;
import CRM.tool.TimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LetterAction extends ActionSupport implements ModelDriven<Letter> {
	private Letter form = new Letter();
	public Letter getModel() {
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
	//删除私信的id串
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
	private LetterService letterService;
	public void setLetterService(LetterService letterService) {
		this.letterService = letterService;
	}
	
	//查询
	public String loadByPage() throws ParseException, IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		//分页查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Letter.class);
		
		//只能看到收件人为当前登陆用户的私信
		criteria.add(Restrictions.eq("staff_id",user.getStaff().getId()));
		
		//设置条件
		if(queryCriteria != null){
			
			if(!queryCriteria.getKeywords().equals("")){
				criteria.add(Restrictions.or(Restrictions.like("send_name",queryCriteria.getKeywords(), MatchMode.ANYWHERE)));
			}
			
			
			if(!queryCriteria.getIsRead().equals("")){
				criteria.add(Restrictions.eq("isRead",queryCriteria.getIsRead()));
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
		EasyUIDatagrid datagrid = letterService.loadByPage(criteria,rows,page);
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

	//详情之前的回显
	public String getDetails(){
		Letter letter = letterService.getOne(form.getId());
		if(letter.getIsRead().equals("未读")){
			letterService.setRead(form.getId());
		}
		
		
		ServletActionContext.getRequest().getSession().setAttribute("letter", letter);
		
		return NONE;
	}
	
	
	//新增私信
	public String addLetter() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		letterService.addLetter(user,form);
		ServletActionContext.getResponse().getWriter().print(1);
		
		return NONE;
	}
	
	//删除私信
	public String remove() throws IOException{
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String[] ids = result.split("丨");
		int index = letterService.removeLetter(user,ids);
		
		ServletActionContext.getResponse().getWriter().print(index);
		
		return NONE;
	}
}
