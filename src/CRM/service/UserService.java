package CRM.service;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.User;

public interface UserService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	int removeUsersByIds(User userLogin,String[] arr, String[] staff_names_arr);

	User addUser(User userLogin,User form, String staff_id);

	User editUser(User userLogin,User form);

	User editUserState(User userLogin,User form);

	User login(User form);

	void update(User user, String ip);

	User editUserPassword(User userLogin,User form);

	void editUserStaffNameAndUserId(User userLogin,User user, String staff_id);

}
