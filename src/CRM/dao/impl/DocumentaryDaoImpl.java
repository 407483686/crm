package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.DocumentaryDao;
import CRM.entity.Documentary;
import CRM.entity.Inlib;

public class DocumentaryDaoImpl implements DocumentaryDao {
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

		List<Documentary> list = (List<Documentary>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}


	
	
	public int removeDocumentary(String[] ids) {
		int index = 0;
		for (String id : ids) {
			Documentary documentary = hibernateTemplate.load(Documentary.class,Integer.parseInt(id));
			hibernateTemplate.delete(documentary);
			index += 1;
		}
		
		return index;
	}


	public Documentary getOne(int id) {
		List<Documentary> list = (List<Documentary>) hibernateTemplate.find("from Documentary where id=?", id);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}


	public void edit(Documentary form) {
		Documentary docu = hibernateTemplate.load(Documentary.class,form.getId());
		docu.setWay(form.getWay());
		docu.setEvolve(form.getEvolve());
		docu.setRemark(form.getRemark());
		hibernateTemplate.update(docu);
	}


	public void add(Documentary form) {
		hibernateTemplate.save(form);
	}


	
	
	public void updateEvolve(String documentary_id) {
		Documentary docu = (Documentary) hibernateTemplate.find("from Documentary where id = ? ",Integer.parseInt(documentary_id)).get(0);
		docu.setEvolve("已成交");
		hibernateTemplate.save(docu);
	}
}
