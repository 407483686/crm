package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.LogDao;
import CRM.entity.Client;
import CRM.entity.Log;

public class LogDaoImpl implements LogDao {
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void addLog(Log form){
		hibernateTemplate.save(form);
	}

	
	
	public int findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(criteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		
		return 0;
	}

	public List<?> loadByPage(DetachedCriteria criteria, int i, int rows) {
		criteria.setProjection(null);

		List<Log> list = (List<Log>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}
}
