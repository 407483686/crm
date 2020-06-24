package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.InlibDao;
import CRM.entity.Inlib;
import CRM.entity.Product;

public class InlibDaoImpl implements InlibDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//新增入库
	public void addInlib(Inlib form,String product_id) {
		Product product = (Product) hibernateTemplate.find("from Product where id = ?",Integer.parseInt(product_id)).get(0);
		form.setProduct(product);
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

		List<Inlib> list = (List<Inlib>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}

	
	//加载详情
	public Inlib getOne(int id) {
		Inlib inlib = (Inlib) hibernateTemplate.find("from Inlib where id=?",id).get(0);
		return inlib;
	}
	
}
