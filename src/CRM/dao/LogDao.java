package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Log;

public interface LogDao {
	public void addLog(Log form);

	public int findCount(DetachedCriteria criteria);

	public List<?> loadByPage(DetachedCriteria criteria, int i, int rows);
}
