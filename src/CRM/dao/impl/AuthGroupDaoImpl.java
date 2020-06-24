package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.AuthGroupDao;
import CRM.entity.AuthGroup;
import CRM.entity.Client;

public class AuthGroupDaoImpl implements AuthGroupDao {
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//根据主键id查找出授权
	public String loadAuths(int groupId) {
		AuthGroup ag = (AuthGroup) hibernateTemplate.find("from AuthGroup where id = ?", groupId).get(0);
		return ag.getAuths();
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

		List<AuthGroup> list = (List<AuthGroup>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}

	
	//保存授权
	public void saveAuths(String auth_group_id, String auths) {
		AuthGroup ag = (AuthGroup) hibernateTemplate.find("from AuthGroup where id = ?",Integer.parseInt(auth_group_id)).get(0);
		ag.setAuths(auths);
		hibernateTemplate.update(ag);
	}
}
