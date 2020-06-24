package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.InformDao;
import CRM.entity.Client;
import CRM.entity.Inform;

public class InformDaoImpl implements InformDao {
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

		List<Inform> list = (List<Inform>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}


	public Inform getOne(int id) {
		Inform inform = (Inform) hibernateTemplate.find("from Inform where id = ?", id).get(0);
		return inform;
	}


	public void add(Inform form) {
		hibernateTemplate.save(form);
	}


	public void update(Inform inform) {
		hibernateTemplate.update(inform);
	}


	public void remove(String id) {
		Inform inform = (Inform) hibernateTemplate.find("from Inform where id = ?", Integer.parseInt(id)).get(0);
		hibernateTemplate.delete(inform);
	}
}
