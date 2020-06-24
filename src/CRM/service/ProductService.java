package CRM.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Product;
import CRM.entity.ProductExtend;
import CRM.entity.User;

public interface ProductService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	int removeProduct(User user,String[] ids);

	boolean addProduct(User user,Product form, ProductExtend productExtend);

	Product getOne(int id);

	void editProduct(User user,Product form, ProductExtend productExtend);

	List<Product> loadAll();

}
