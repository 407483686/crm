package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Inlib {
	@Expose
	private int id;
	//入库产品的id
	@Expose
	private Product product;
	//入库的数量
	@Expose
	private int number;
	//经办人的名字,char(20)
	@Expose
	private String staff_name;
	//入库方式,char(2)
	@Expose
	private String mode;
	//入库方式说明,char(20)
	@Expose
	private String mode_explain;
	//录入员,char(20),这是从session中得到的user
	@Expose
	private String enter;
	//创建时间，这是在后台创建的
	@Expose
	private Date create_time;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getMode_explain() {
		return mode_explain;
	}
	public void setMode_explain(String mode_explain) {
		this.mode_explain = mode_explain;
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
	@Override
	public String toString() {
		return "Inlib [id=" + id + ", product=" + product + ", number="
				+ number + ", staff_name=" + staff_name + ", mode=" + mode
				+ ", mode_explain=" + mode_explain + ", enter=" + enter
				+ ", create_time=" + create_time + "]";
	}
	
	
}
