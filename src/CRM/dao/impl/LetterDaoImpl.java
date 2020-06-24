package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.LetterDao;
import CRM.entity.Client;
import CRM.entity.Letter;

public class LetterDaoImpl implements LetterDao {
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

		List<Letter> list = (List<Letter>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}
	public Letter getOne(int id) {
		Letter letter = (Letter) hibernateTemplate.find("from Letter where id = ?",id).get(0);
		return letter;
	}
	
	public void setRead(int id) {
		Letter letter = (Letter) hibernateTemplate.find("from Letter where id = ?",id).get(0);
		letter.setIsRead("已读");
		hibernateTemplate.update(letter);
	}
	public void add(Letter form) {
		hibernateTemplate.save(form);
	}
	
	public int removeLetter(String[] ids) {
		int index = 0;
		for(String id : ids){
			Letter letter = hibernateTemplate.load(Letter.class,Integer.parseInt(id));
			hibernateTemplate.delete(letter);
			index += 1;
		}
		
		return index;
	}
}
