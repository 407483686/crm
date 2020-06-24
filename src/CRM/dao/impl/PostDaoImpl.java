package CRM.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import org.hibernate.criterion.Expression;

import CRM.dao.PostDao;
import CRM.entity.Post;
import CRM.entity.QueryCriteria;

public class PostDaoImpl implements PostDao {
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
		List<Post> list = (List<Post>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}
	public Post add(Post form) {
		hibernateTemplate.save(form);
		//保存成功后实体类就有对应的主键了
		return form;
	}
	public Post loadByName(Post form) {
		List<Post> list = (List<Post>) hibernateTemplate.find("from Post where name=?",form.getName());
		if(list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	public int updatePost(Post form) {
		String hql = "update Post p set p.name=? where p.id=?";
		Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		query.setString(0,form.getName());
		query.setInteger(1,form.getId());
		
		return query.executeUpdate();
	}
	public int removeByIds(String[] ids) {
		int index = 0;
		for (String id : ids) {
			Post post = hibernateTemplate.load(Post.class,Integer.parseInt(id));
			hibernateTemplate.delete(post);
			index += 1;
		}
		
		return index;
	}

	
	public List<Post> load() {
		
		List<Post> list = (List<Post>) hibernateTemplate.find("from Post");
		if(list.size() > 0){
			return list;
		}
		return null;
	}
	
}
