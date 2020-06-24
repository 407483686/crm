package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.StaffDao;
import CRM.entity.AuthGroupAccess;
import CRM.entity.Extend;
import CRM.entity.Staff;
import CRM.entity.User;

public class StaffDaoImpl implements StaffDao {
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public int findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(criteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		
		return 0;
	}

	public List<?> loadByPage(DetachedCriteria criteria, int i, int rows) {
		criteria.setProjection(null);

		List<Staff> list = (List<Staff>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}
	
	
	//根据 员工编号进行查询
	public Staff loadByNumber(String number) {
		List<Staff> list = (List<Staff>) hibernateTemplate.find("from Staff where number=?",number);
		if(list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	//根据身份证进行查询
	public Staff loadByIdCard(String id_card) {
		List<Staff> list = (List<Staff>) hibernateTemplate.find("from Staff where id_card=?",id_card);
		if(list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}

	//级联保存
	public void saveStaff(Staff form, Extend extend) {
		form.setExtend(extend);
		extend.setStaff(form);
		hibernateTemplate.save(form);
	}
	
	
	//根据id串来删除员工档案
	public int removeStaffs(String[] ids) {
		int index = 0;
		for(String id : ids){
			Staff staff = hibernateTemplate.load(Staff.class,Integer.parseInt(id));
			if(staff.getUser() == null){
				hibernateTemplate.delete(staff);
				index += 1;
			}
		}
		
		return index;
	}

	public Staff loadOne(int id) {
		//如果要加载成json串输出最好不要用load方法，load方法会默认使用延迟加载除非你设置了false，而find则是直接去一二级缓存或者数据库查询
		Staff staff = (Staff) hibernateTemplate.find("from Staff where id = ?",id).get(0);
		
		return staff;
	}

	public void edit(Staff form, Extend extendForm,String group_id) {
		Staff staff = (Staff) hibernateTemplate.find("from Staff where id=?",form.getId()).get(0);
		Extend extend = staff.getExtend();
		AuthGroupAccess aa = staff.getAuthGroupAccess();
		
		if(!group_id.equals("")){
			aa.setGroupId(Integer.parseInt(group_id));
		}
		
		staff.setGender(form.getGender());
		
		
		if(form.getPost() != null){
			staff.setPost(form.getPost());
		}
		
		if(form.getTel() != null){
			staff.setTel(form.getTel());
		}
		
		if(form.getType() != null){
			staff.setType(form.getType());
		}
		
		if(form.getMarital_status() != null){
			staff.setMarital_status(form.getMarital_status());
		}
		
		if(form.getNation() != null){
			staff.setNation(form.getNation());
		}
		
		if(form.getEntry_date() != null){
			staff.setEntry_date(form.getEntry_date());
		}
		
		if(form.getEducation() != null){
			staff.setEducation(form.getEducation());
		}
		
		if(form.getDimission_date() != null){
			staff.setDimission_date(form.getDimission_date());
		}
		
		if(form.getEntry_status() != null){
			staff.setEntry_status(form.getEntry_status());
		}
		
		if(extendForm.getMajor() != null){
			extend.setMajor(extendForm.getMajor());
		}
		
		if(form.getPolitics_status() != null){
			staff.setPolitics_status(form.getPolitics_status());
		}
		
		if(extendForm.getHeath() != null){
			extend.setHeath(extendForm.getHeath());
		}
		
		if(extendForm.getResidence() != null){
			extend.setResidence(extendForm.getResidence());
		}
		
		if(extendForm.getGraduation_time() != null){
			extend.setGraduation_time(extendForm.getGraduation_time());
		}
		
		if(extendForm.getRegistered_permanent_residence() != null){
			extend.setRegistered_permanent_residence(extendForm.getRegistered_permanent_residence());
		}
		
		if(extendForm.getSchool() != null){
			extend.setSchool(extendForm.getSchool());
		}
		
		if(extendForm.getIntro() != null){
			extend.setIntro(extendForm.getIntro());
		}
		
		if(extendForm.getDetails() != null){
			extend.setDetails(extendForm.getDetails());
		}
		
		staff.setExtend(extend);
		extend.setStaff(staff);
		
		staff.setAuthGroupAccess(aa);
		aa.setStaff(staff);
		
		hibernateTemplate.update(staff);
	}

	
	//给员工档案绑定userid
	public void setUserId(String staff_id, User user) {
		Staff staff = (Staff) hibernateTemplate.find("from Staff where id = ?",Integer.parseInt(staff_id)).get(0);
		staff.setUser(user);
		hibernateTemplate.update(staff);
	}

	
	//根据传过来的名字清除userid
	public void removeUserId(String[] staff_ids_arr) {
		for (String staff_id : staff_ids_arr) {
			Staff staffGet = (Staff) hibernateTemplate.find("from Staff where id=?",Integer.parseInt(staff_id)).get(0);
			staffGet.setUser(null);
			hibernateTemplate.update(staffGet);
		}
	}

	
	//根据userid来查找staff，再把它的userid清空
	public void removeOneUserId(int userid) {
		Staff staff = (Staff) hibernateTemplate.find("from Staff where user.id =?",userid).get(0);
		staff.setUser(null);
		hibernateTemplate.update(staff);
	}

}
