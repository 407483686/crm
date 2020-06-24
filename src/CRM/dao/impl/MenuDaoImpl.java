package CRM.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.MenuDao;
import CRM.entity.Menu;

public class MenuDaoImpl implements MenuDao{
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//根据父菜单的id来加载菜单
	public List<Menu> loadByPid(int pid) {
		List<Menu> list = (List<Menu>) hibernateTemplate.find("from Menu where pid =?",pid);
	
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}

	
	//不管父菜单还是子菜单全部加载
	public List<Menu> loadAll() {
		return (List<Menu>) hibernateTemplate.find("from Menu");
	}
	
	
}
