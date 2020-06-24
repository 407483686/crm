package CRM.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Order;
import CRM.entity.Outlib;
import CRM.entity.User;

public interface OrderService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	Order getOne(int id);

	void addOrder(User user, Order form, List<Outlib> outlibList, String documentary_id);

	List<Outlib> getOutlibList(String order_sn);

	void updateOrder(User user,int id, String details);

}
