package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.UserDao;
import CRM.entity.Post;
import CRM.entity.Staff;
import CRM.entity.User;

public class UserDaoImpl implements UserDao{
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//查询总记录数
	public int findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(criteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		
		return 0;
	}
	
	//分页查询
	public List<?> loadByPage(DetachedCriteria criteria, int i, int rows) {
		criteria.setProjection(null);
		List<User> list = (List<User>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}
	
	//根据id删除
	public void removeByIds(String id) {
		User user = hibernateTemplate.load(User.class,Integer.parseInt(id));
		hibernateTemplate.clear();
		hibernateTemplate.delete(user);
	}
	
	
	//根据用户名查询
	public User loadByName(User form) {
		List<User> list = (List<User>) hibernateTemplate.find("from User where accounts=?",form.getAccounts());
		if(list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	//添加用户
	public User add(User form) {
		hibernateTemplate.saveOrUpdate(form);
		return form;
	}

	
	//修改用户的密码和状态
	public User updatePasswordAndState(User form) {
		User user = hibernateTemplate.load(User.class,form.getId());
		user.setPassword(form.getPassword());
		user.setState(form.getState());
		hibernateTemplate.update(user);
		
		return user;
	}

	//根据id查询用户
	public User loadById(User form) {
		User user = hibernateTemplate.load(User.class,form.getId());
		if(null != user){
			return user;
		}
		
		return null;
	}

	
	
	//单独修改状态
	public User updateState(User form) {
		User user = (User) hibernateTemplate.find("from User where id = ?",form.getId()).get(0);
		user.setState(form.getState());
		hibernateTemplate.update(user);
		
		return user;
	}

	public User loadByNameAndPassword(User form) {
		List<User> list = (List<User>) hibernateTemplate.find("from User where accounts=? and password=?",form.getAccounts(),form.getPassword());
		
		if(list.size() > 0){
			return list.get(0); 
		}
		
		return null;
	}

	public void update(User user) {
		hibernateTemplate.update(user);
	}

	public User updatePassword(User form) {
		User user = hibernateTemplate.load(User.class,form.getId());
		user.setPassword(form.getPassword());
		hibernateTemplate.update(user);
		
		return form;
	}

	
	

	
	
	
	
	
}
