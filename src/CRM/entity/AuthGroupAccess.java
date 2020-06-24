package CRM.entity;

import com.google.gson.annotations.Expose;

public class AuthGroupAccess {
	//主键
	@Expose
	private int sid;
	//授权组id
	@Expose
	private int groupId;
	//关联的用户
	private Staff staff;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	
}
