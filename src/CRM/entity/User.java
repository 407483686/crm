package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

//登录账号类
public class User {
	@Expose
	private int id;
	//账号有一个名称
	@Expose
	private String accounts;
	//账号的密码
	@Expose
	private String password;
	//账号的最后登录时间
	@Expose
	private Date last_login_time;
	//账号最后登录的ip
	@Expose
	private String last_login_ip;
	//账号登录的次数
	@Expose
	private int login_count;
	//账号的状态，时正常还是冻结
	@Expose
	private String state;
	//账号创建的时间
	@Expose
	private Date create_time;
	//账号对应的用户
	@Expose
	private Staff staff;

	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccounts() {
		return accounts;
	}
	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}
	public String getLast_login_ip() {
		return last_login_ip;
	}
	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}
	public int getLogin_count() {
		return login_count;
	}
	public void setLogin_count(int login_count) {
		this.login_count = login_count;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
