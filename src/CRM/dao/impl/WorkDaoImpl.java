package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.WorkDao;
import CRM.entity.Client;
import CRM.entity.Work;

public class WorkDaoImpl implements WorkDao {
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
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

		List<Work> list = (List<Work>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}

	public void addWork(Work form) {
		hibernateTemplate.saveOrUpdate(form);
	}

	public Work getOne(int id) {
		Work work = (Work) hibernateTemplate.find("from Work where id = ?",id).get(0);
		return work;
	}
}
