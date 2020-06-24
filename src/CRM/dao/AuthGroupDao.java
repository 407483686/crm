package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface AuthGroupDao {

	String loadAuths(int groupId);

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	void saveAuths(String auth_group_id, String auths);

}
