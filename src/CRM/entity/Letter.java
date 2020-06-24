package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Letter {
	//主键
	@Expose
	private int id;
	//私信消息
	@Expose
	private String message;
	//收件人id
	@Expose
	private Integer staff_id;
	//收件人名字,varchar(20)
	@Expose
	private String staff_name;
	//发件人id
	@Expose
	private Integer send_id;
	//发件人名字,varchar(20)
	@Expose
	private String send_name;
	//是否已读,char(2)
	@Expose
	private String isRead;
	//创建时间
	@Expose
	private Date create_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public Integer getSend_id() {
		return send_id;
	}
	public void setSend_id(Integer send_id) {
		this.send_id = send_id;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
