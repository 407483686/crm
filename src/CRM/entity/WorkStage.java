package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class WorkStage {
	//这个由于是附属表，就不建立三层对象了，直接在work里面操作
	//主键
	@Expose
	private int id;
	//工作阶段名称
	@Expose
	private String title;
	//工作阶段创建时间
	@Expose
	private Date create_time;
	
	private Work work;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
