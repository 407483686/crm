package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Inform;

public interface InformDao {

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	Inform getOne(int id);

	void add(Inform form);

	void update(Inform inform);

	void remove(String id);

}
