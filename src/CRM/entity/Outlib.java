package CRM.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Outlib {
	@Expose
	private int id;
	//产品id，只用于出库操作时减少库存，其他全都用属性，因为这是不想关联，又不想多表查询的唯一办法
	@Expose
	private int product_id;
	//产品编号,char(5)
	@Expose
	private String product_sn;
	//产品名称
	@Expose
	private String product_name;
	//销售价格
	@Expose
	private BigDecimal sell_price;
	//订单号，是在service生成后赋予的,char(14)
	@Expose
	private String order_sn;
	//产品数量
	@Expose
	private int number;
	//出库状态,char(5)，默认应该是未处理
	@Expose
	private String state;
	@Override
	public String toString() {
		return "Outlib [id=" + id + ", product_id=" + product_id
				+ ", product_sn=" + product_sn + ", product_name="
				+ product_name + ", sell_price=" + sell_price + ", order_sn="
				+ order_sn + ", number=" + number + ", state=" + state
				+ ", clerk=" + clerk + ", enter=" + enter + ", dispose_time="
				+ dispose_time + ", create_time=" + create_time + "]";
	}
	//出货员,char(20)，是在出库操作添加的，和录入员一样session的用户
	@Expose
	private String clerk;
	//录入员,char(20),和订单的录入员一样，在service设置
	@Expose
	private String enter;
	//出库时间，应该是在出库操作添加的
	@Expose
	private Date dispose_time;
	//创建时间
	@Expose
	private Date create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getClerk() {
		return clerk;
	}
	public void setClerk(String clerk) {
		this.clerk = clerk;
	}
	public String getEnter() {
		return enter;
	}
	public void setEnter(String enter) {
		this.enter = enter;
	}
	public Date getDispose_time() {
		return dispose_time;
	}
	public void setDispose_time(Date dispose_time) {
		this.dispose_time = dispose_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getProduct_sn() {
		return product_sn;
	}
	public void setProduct_sn(String product_sn) {
		this.product_sn = product_sn;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public BigDecimal getSell_price() {
		return sell_price;
	}
	public void setSell_price(BigDecimal sell_price) {
		this.sell_price = sell_price;
	}
	
	
}
