package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Documentary;

public interface DocumentaryDao {

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	int removeDocumentary(String[] ids);

	Documentary getOne(int id);

	void edit(Documentary form);

	void add(Documentary form);

	void updateEvolve(String documentary_id);

}
