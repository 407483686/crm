package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Client {
	//主键
	@Expose
	private int id;
	//公司名称,char(30)
	@Expose
	private String company;
	//联系人,char(20)
	@Expose
	private String name;
	//手机号码,char(11)
	@Expose
	private String tel;
	//客户来源,char(20)
	@Expose
	private String source;
	//录入员,char(20)
	@Expose
	private String enter;
	//创建时间
	@Expose
	private Date create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
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
