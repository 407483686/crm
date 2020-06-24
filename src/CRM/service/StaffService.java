package CRM.service;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Extend;
import CRM.entity.Staff;
import CRM.entity.User;

public interface StaffService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	boolean addStaff(User user,Staff form, Extend extend);

	int removeStaff(User user,String[] ids);

	Staff getOne(int id);

	void editStaff(User user,Staff form, Extend extend, String group_id);


}
