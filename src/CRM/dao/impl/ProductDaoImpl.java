package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.ProductDao;
import CRM.entity.Inlib;
import CRM.entity.Product;
import CRM.entity.ProductExtend;
import CRM.entity.Staff;
import CRM.entity.User;

public class ProductDaoImpl implements ProductDao {
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
	//查询每页内容
	public List<?> loadByPage(DetachedCriteria criteria, int i, int rows) {
		criteria.setProjection(null);

		List<Inlib> list = (List<Inlib>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}

	
	//删除
	public int removeProducts(String[] ids) {
		int index = 0;
		for(String id : ids){
			Product product = hibernateTemplate.load(Product.class,Integer.parseInt(id));
			hibernateTemplate.delete(product);
			index += 1;
		}
		
		return index;
	}

	
	//根据产品名称查询产品信息
	public Product loadByName(String name) {
		List<Product> list = (List<Product>) hibernateTemplate.find("from Product where name=?",name);

		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	//根据产品编号查询产品信息
	public Product loadBySn(String sn) {
		List<Product> list = (List<Product>) hibernateTemplate.find("from Product where sn=?",sn);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	//新增产品信息
	public void saveProduct(Product form,ProductExtend productExtend) {
		form.setProductExtend(productExtend);
		productExtend.setProduct(form);
		hibernateTemplate.save(form);
	}

	public Product loadOne(int id) {
		Product product = (Product) hibernateTemplate.find("from Product where id=?",id).get(0);
			
		return product;
	}

	//修改
	//因为用户可能只修改一部分，那么我们就要进行判断
	public void edit(Product form, ProductExtend productExtendForm) {
		Product product = (Product) hibernateTemplate.find("from Product where id = ?",form.getId()).get(0);
		ProductExtend productExtend = product.getProductExtend();
		
		if(!form.getType().equals("")){
			product.setType(form.getType());
		}
		
		if(form.getPro_price() != null){
			product.setPro_price(form.getPro_price());
		}
		
		if(form.getSell_price() != null){
			product.setSell_price(form.getSell_price());
		}
		
		if(form.getInventory_alarm() != null){
			product.setInventory_alarm(form.getInventory_alarm());
		}
		
		if(!form.getUnit().equals("")){
			product.setUnit(form.getUnit());
		}
		
		if(!productExtendForm.getDetails().equals("")){
			productExtend.setDetails(productExtendForm.getDetails());
		}
		
		product.setProductExtend(productExtend);
		productExtend.setProduct(product);
		hibernateTemplate.update(product);
	}

	
	
	//改变库存量和入库量
	public void changeInventoryAndInventoryIn(String product_id, int number) {
		Product product = (Product) hibernateTemplate.find("from Product where id=?",Integer.parseInt(product_id)).get(0);
		product.setInventory(product.getInventory() + number);
		product.setInventory_in(product.getInventory_in() + number);
		hibernateTemplate.update(product);
	}

	
	
	//设置库存量，根据出库记录中的id和数量
	public void setKuCun(int product_id, int number) {
		Product product = (Product) hibernateTemplate.find("from Product where id=?",product_id).get(0);
		//减少库存量
		product.setInventory(product.getInventory() - number);
		//增加出库量
		product.setInventory_out(product.getInventory_out() + number);
	}

	
	
	//图标查询方法
	public List<Product> loadAll() {
		return (List<Product>) hibernateTemplate.find("from Product");
	}
	
}
