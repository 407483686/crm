package CRM.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.annotations.Expose;

public class Product {
	//主键
	@Expose
	private int id;
	//产品编号,char(5)
	@Expose
	private String sn;
	//产品名称,char(20)
	@Expose
	private String name;
	//产品类别,char(10)
	@Expose
	private String type;
	//采购价格,decimal(10)
	@Expose
	private BigDecimal pro_price;
	//销售价格,decimal(10)
	@Expose
	private BigDecimal sell_price;
	//计量单位，如个、卷,char(5)
	@Expose
	private String unit;
	//库存量,因为这些int类型，不是id，有可能为空，不设置为Integer的话，就会报错，因为int不能为null
	@Expose
	private Integer inventory;
	//入库总量
	@Expose
	private Integer inventory_in;
	//出库总量
	@Expose
	private Integer inventory_out;
	//库存警报量
	@Expose
	private Integer inventory_alarm;
	//创建时间
	@Expose
	private Date create_time;
	@Expose
	private ProductExtend productExtend; 
	
	public ProductExtend getProductExtend() {
		return productExtend;
	}
	public void setProductExtend(ProductExtend productExtend) {
		this.productExtend = productExtend;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getPro_price() {
		return pro_price;
	}
	public void setPro_price(BigDecimal pro_price) {
		this.pro_price = pro_price;
	}
	public BigDecimal getSell_price() {
		return sell_price;
	}
	public void setSell_price(BigDecimal sell_price) {
		this.sell_price = sell_price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Integer getInventory_in() {
		return inventory_in;
	}
	public void setInventory_in(Integer inventory_in) {
		this.inventory_in = inventory_in;
	}
	public Integer getInventory_out() {
		return inventory_out;
	}
	public void setInventory_out(Integer inventory_out) {
		this.inventory_out = inventory_out;
	}
	public Integer getInventory_alarm() {
		return inventory_alarm;
	}
	public void setInventory_alarm(Integer inventory_alarm) {
		this.inventory_alarm = inventory_alarm;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
	
}
