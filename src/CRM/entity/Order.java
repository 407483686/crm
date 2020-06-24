package CRM.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Order {
	@Expose
	private int id;
	//编号，按照日期时间，char14
	@Expose
	private String sn;
	//标题，订单名称,char30
	@Expose
	private String title;
	//关联的跟单记录
	//用来加载列表上的跟单员和公司名，以及详情中的内容，以及对跟单的状态进行更新
	@Expose
	private Documentary documentary;
	//原价
	@Expose
	private BigDecimal original;
	//现价
	@Expose
	private BigDecimal cost;
	//支付状态,char(10)
	@Expose
	private String pay_state;
	//录入员,char(20)
	@Expose
	private String enter;
	//创建时间
	@Expose
	private Date create_time;
	//详情
	@Expose
	private String details;
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Documentary getDocumentary() {
		return documentary;
	}
	public void setDocumentary(Documentary documentary) {
		this.documentary = documentary;
	}
	public BigDecimal getOriginal() {
		return original;
	}
	public void setOriginal(BigDecimal original) {
		this.original = original;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getPay_state() {
		return pay_state;
	}
	public void setPay_state(String pay_state) {
		this.pay_state = pay_state;
	}
	public String getEnter() {
		return enter;
	}
	public void setEnter(String enter) {
		this.enter = enter;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
	
}
