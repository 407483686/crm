package CRM.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Receipt {
	//主键
	@Expose
	private int id;
	//收款的标题,char(30)
	@Expose
	private String title;
	//订单编号，和出库记录一样绑定订单编号，等收款时根据订单编号去查找,char(12)
	@Expose
	private String order_sn;
	//订单金额
	@Expose
	private BigDecimal cost;
	//录入员,char(20)
	@Expose
	private String enter;
	//简易备注,char(20)
	@Expose
	private String remark;
	//创建时间
	@Expose
	private Date create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getEnter() {
		return enter;
	}
	public void setEnter(String enter) {
		this.enter = enter;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
}
