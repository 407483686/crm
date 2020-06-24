package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Extend;
import CRM.entity.Staff;
import CRM.entity.User;

public interface StaffDao {

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	Staff loadByNumber(String number);

	Staff loadByIdCard(String id_card);

	void saveStaff(Staff form, Extend extend);

	int removeStaffs(String[] ids);

	Staff loadOne(int id);

	void edit(Staff form, Extend extend, String group_id);

	void setUserId(String staff_id, User userSave);

	void removeUserId(String[] staff_names_arr);

	void removeOneUserId(int id);

}
