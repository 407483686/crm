package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Product;
import CRM.entity.ProductExtend;

public interface ProductDao {

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	int removeProducts(String[] ids);

	Product loadByName(String name);

	Product loadBySn(String sn);

	void saveProduct(Product form, ProductExtend productExtend);

	Product loadOne(int id);

	void edit(Product form, ProductExtend productExtend);

	void changeInventoryAndInventoryIn(String product_id, int number);

	void setKuCun(int product_id, int number);

	List<Product> loadAll();

}
