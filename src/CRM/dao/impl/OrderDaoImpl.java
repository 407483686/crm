package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.OrderDao;
import CRM.entity.Client;
import CRM.entity.Order;

public class OrderDaoImpl implements OrderDao {
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

		List<Order> list = (List<Order>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}

	public Order getOne(int id) {
		Order order = (Order) hibernateTemplate.find("from Order where id = ?", id).get(0);
		return order;
	}

	public void saveOrder(Order form) {
		hibernateTemplate.save(form);
	}

	
	public void updateOrder(int id, String details) {
		Order order = hibernateTemplate.load(Order.class,id);
		order.setDetails(details);
		hibernateTemplate.update(order);
	}

	
	//改变订单的状态为已付款
	public void changePayStateBySn(String order_sn) {
		Order order = (Order) hibernateTemplate.find("from Order where sn = ?", order_sn).get(0);
		order.setPay_state("已支付");
		hibernateTemplate.update(order);
	}
	
}
