package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Log {
	//主键
	@Expose
	private int id;
	//登陆用户名和真实姓名,char(20)
	@Expose
	private String user;
	//操作的方法类型,char(20),即调用了哪个模块下的哪种方法，比如添加用户信息
	@Expose
	private String method;
	//操作模块,char(20)
	@Expose
	private String module;
	//操作的ip,char(15)
	@Expose
	private String ip;
	//创建时间
	@Expose
	private Date create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
}
