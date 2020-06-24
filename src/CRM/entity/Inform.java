package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Inform {
	//主键
	@Expose
	private int id;
	//通知标题,varchar(50)
	@Expose
	private String title;
	//通知内容,text
	@Expose
	private String details;
	//发布者id
	@Expose
	private Integer staff_id;
	//发布者名字,varchar(20)
	@Expose
	private String staff_name;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	
}
