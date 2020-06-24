package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Inlib;

public interface InlibDao {

	void addInlib(Inlib form, String product_id);

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	Inlib getOne(int id);

}
