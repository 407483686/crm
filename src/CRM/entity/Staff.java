package CRM.entity;

import java.util.Date;

import com.google.gson.annotations.Expose;

//员工档案
public class Staff {
	//主键
	@Expose
	private int id;
	//员工真实姓名,char(20)
	@Expose
	private String name;
	//员工编号，从1001开始
	@Expose
	private String number;
	//员工性别
	@Expose
	private char gender;
	//员工类型,char(4)
	@Expose
	private String type;
	//员工身份证,char(18)
	@Expose
	private String id_card;
	//职位部门
	@Expose
	private String post;
	//员工手机号码,char(11)
	@Expose
	private String tel;
	//员工民族,char(5)
	@Expose
	private String nation;
	//员工婚姻状况,char(2)
	@Expose
	private String marital_status;
	//员工在职情况,char(2)
	@Expose
	private String entry_status;
	//员工入职时间
	@Expose
	private Date entry_date;
	//员工离职时间
	@Expose
	private Date dimission_date;
	//员工政治面貌,char(2)
	@Expose
	private String politics_status;
	//员工学历,char(2)
	@Expose
	private String education;
	//员工档案创建时间
	@Expose
	private Date create_time;
	//添加一个一对一单向（指级联）主键关联对象，用来设置一对一的级联
	//事实上级联的前提正式两个都有对方的对象，然后get之后设置是否生效，就是看级联了，单双向指的不是是否有对象（都会有），而指的是级联的方向
	//one-to-one的property-ref是用于外键关联非主键关联，因为外键关联不设置property-ref的话，从表会拿自己的id和主表的id进行关联
	@Expose
	private Extend extend;
	//账号表的主键作为外键
	private User user;
	//关联的授权组id
	@Expose
	private AuthGroupAccess authGroupAccess;
	
	public AuthGroupAccess getAuthGroupAccess() {
		return authGroupAccess;
	}
	public void setAuthGroupAccess(AuthGroupAccess authGroupAccess) {
		this.authGroupAccess = authGroupAccess;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Extend getExtend() {
		return extend;
	}
	public void setExtend(Extend extend) {
		this.extend = extend;
	}
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String id_card) {
		this.id_card = id_card;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getEntry_status() {
		return entry_status;
	}
	public void setEntry_status(String entry_status) {
		this.entry_status = entry_status;
	}
	public Date getEntry_date() {
		return entry_date;
	}
	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}
	public Date getDimission_date() {
		return dimission_date;
	}
	public void setDimission_date(Date dimission_date) {
		this.dimission_date = dimission_date;
	}
	public String getPolitics_status() {
		return politics_status;
	}
	public void setPolitics_status(String politics_status) {
		this.politics_status = politics_status;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", number=" + number
				+ ", gender=" + gender + ", type=" + type + ", id_card="
				+ id_card + ", tel=" + tel + ", nation=" + nation
				+ ", marital_status=" + marital_status + ", entry_status="
				+ entry_status + ", entry_date=" + entry_date
				+ ", dimission_date=" + dimission_date + ", politics_status="
				+ politics_status + ", education=" + education
				+ ", create_time=" + create_time + "]";
	}
	
	
}
