package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class AuthGroup {
	//主键
	@Expose
	private int id;
	//权限组对应的职位
	@Expose
	private String title;
	//每组对应的权限
	@Expose
	private String auths;
	//创建时间
	@Expose
	private Date create_time;
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
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
	public String getAuths() {
		return auths;
	}
	public void setAuths(String auths) {
		this.auths = auths;
	}
	
	
}
