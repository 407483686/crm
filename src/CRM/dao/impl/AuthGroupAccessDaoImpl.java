package CRM.dao.impl;

import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.AuthGroupAccessDao;

public class AuthGroupAccessDaoImpl implements AuthGroupAccessDao {
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
