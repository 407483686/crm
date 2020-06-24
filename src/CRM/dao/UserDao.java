package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.User;

public interface UserDao {

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	void removeByIds(String id);

	User loadByName(User form);

	User add(User form);

	User updatePasswordAndState(User form);

	User loadById(User form);

	User updateState(User form);

	User loadByNameAndPassword(User form);

	void update(User user);

	User updatePassword(User form);


}
