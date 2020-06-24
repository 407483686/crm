package CRM.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Work {
	//主键
	@Expose
	private int id;
	//工作计划标题,char(20)
	@Expose
	private String title;
	//工作计划类型,char(5)
	@Expose
	private String type;
	//任务进度,char(20)，在创建工作时赋值，初始应该为“创建工作任务”
	@Expose
	private String stage;
	//任务状态,char(10)，在创建工作时赋值，初始应该为“进行中”
	@Expose
	private String state;
	//开始时间
	@Expose
	private Date state_date;
	//结束时间
	@Expose
	private Date end_date;
	//实施人id，在创建工作时赋值，值为当前登录用户的id
	@Expose
	private Integer staff_id;
	//实施人名字,char(20)，在创建工作时赋值，值为当前登录用户的名字，这两个实施人名字和id如果是别人指派的，应该会有一个可以选择的员工档案
	@Expose
	private String staff_name;
	//发起人,char(20)，在创建工作时赋值，值为当前登录用户的名称
	@Expose
	private String enter;
	//发起人id
	@Expose
	private Integer enter_id;
	//创建时间，在创建工作时赋值，值为当前时间
	@Expose
	private Date create_time;
	
	@Expose
	private Set<WorkStage> workStages = new HashSet<WorkStage>();
	
	public Integer getEnter_id() {
		return enter_id;
	}
	public void setEnter_id(Integer enter_id) {
		this.enter_id = enter_id;
	}
	public Set<WorkStage> getWorkStages() {
		return workStages;
	}
	public void setWorkStages(Set<WorkStage> workStages) {
		this.workStages = workStages;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getState_date() {
		return state_date;
	}
	public void setState_date(Date state_date) {
		this.state_date = state_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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
