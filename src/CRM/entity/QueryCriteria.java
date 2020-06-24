package CRM.entity;

import java.sql.Timestamp;

//查询条件类，用于接收可能传递来的查询条件
public class QueryCriteria {
	//关键字，用于模糊查询
	private String keywords;
	//时间类型，比如实体类的创建时间和其他时间等
	private String dateType;
	//起始时间
	private String dateFrom;
	//结束时间
	private String dateTo;
	//用户状态
	private String state;
	//入职状态
	private String entry_status;
	//性别,遇到空指针异常，应该是如果字段有内容那么都可以接收，如果无内容，只能用String接收空字符串
	private String gender;
	//身份证
	private String id_card;
	//婚姻情况
	private String marital_status;
	//民族
	private String nation;
	//职位
	private String post;
	//产品类型
	private String type;
	//工作计划状态
	private String work_state;
	//工作计划类型
	private String work_type;
	//私信是否已读
	private String isRead;
	
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getWork_state() {
		return work_state;
	}
	public void setWork_state(String work_state) {
		this.work_state = work_state;
	}
	public String getWork_type() {
		return work_type;
	}
	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEntry_status() {
		return entry_status;
	}
	public void setEntry_status(String entry_status) {
		this.entry_status = entry_status;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	
}
