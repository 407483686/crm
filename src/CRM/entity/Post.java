package CRM.entity;

import java.sql.Timestamp;

//部门实体类
public class Post {
	private int id;
	private String name;//部门名称
	private Timestamp create_time;//部门创建时间
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	
}
