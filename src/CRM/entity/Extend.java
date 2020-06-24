package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

//这是员工档案的附表--扩展表
public class Extend {
	//这是员工档案的主键
	@Expose
	private int staff_id;
	//员工简介
	@Expose
	private String intro;
	//这个对象并不映射为某一列，而是用来设置generator下的param标签属性值和one-to-one标签(主要是 constrained="true"，表示给本类主键加上外键约束)
	private Staff staff;
	//大学专业
	@Expose
	private String major;
	//健康状况
	@Expose
	private String heath;
	//户口
	@Expose
	private String residence;
	//毕业时间
	@Expose
	private Date graduation_time;
	//户口所在地
	@Expose
	private String registered_permanent_residence;
	//毕业院校
	@Expose
	private String school;
	//详情
	@Expose
	private String details;
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getRegistered_permanent_residence() {
		return registered_permanent_residence;
	}
	public void setRegistered_permanent_residence(
			String registered_permanent_residence) {
		this.registered_permanent_residence = registered_permanent_residence;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public Date getGraduation_time() {
		return graduation_time;
	}
	public void setGraduation_time(Date graduation_time) {
		this.graduation_time = graduation_time;
	}
	public String getHeath() {
		return heath;
	}
	public void setHeath(String heath) {
		this.heath = heath;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	@Override
	public String toString() {
		return "Extend [staff_id=" + staff_id + ", intro=" + intro + "]";
	}

	
}
