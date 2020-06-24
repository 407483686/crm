package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Letter;

public interface LetterDao {

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	Letter getOne(int id);

	void setRead(int id);

	void add(Letter form);


	int removeLetter(String[] ids);

}
