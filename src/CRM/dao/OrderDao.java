package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Order;

public interface OrderDao {

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	Order getOne(int id);

	void saveOrder(Order form);

	void updateOrder(int id, String details);

	void changePayStateBySn(String order_sn);

}
