package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class Documentary {
	//主键
	@Expose
	private int id;
	//编号，按照日期时间，使用date转换,char(14)
	@Expose
	private String sn;
	//跟单名称,char(30)
	@Expose
	private String title;
	//公司名称,char(30),不需要用到client表的id，只需要获取其名字即可，毕竟只是在列表输出
	@Expose
	private String company;
	//跟单方式,char(10)
	@Expose
	private String way;
	//进展阶段,char(10)
	@Expose
	private String evolve;
	//跟单员，char(20),同样不需要staff表的id
	@Expose
	private String staff_name;
	//简要详情,char(20)
	@Expose
	private String remark;
	//录入员，char(20),创建时读取session
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
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getEvolve() {
		return evolve;
	}
	public void setEvolve(String evolve) {
		this.evolve = evolve;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
