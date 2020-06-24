package CRM.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.ProductDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Product;
import CRM.entity.ProductExtend;
import CRM.entity.Staff;
import CRM.entity.User;
import CRM.service.ProductService;

@Transactional
public class ProductServiceImpl implements ProductService {
	//dao层
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	//分页条件查询
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(productDao.findCount(criteria));
		easyUIDatagrid.setRows(productDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}

	
	//删除产品信息
	public int removeProduct(User user,String[] ids) {
		return productDao.removeProducts(ids);
	}

	//添加产品信息
	public boolean addProduct(User user,Product form,ProductExtend productExtend) {
		/*
		 * 有两个需要校验的地方：
		 * 	1.校验产品名称是否存在
		 * 	2.校验产品编号是否已经存在
		 * 
		 * 保存是级联保存
		 * */
		//1.校验产品名称
		Product product = productDao.loadByName(form.getName());
		if(null != product){
			//如果不为空，那么说明该产品名称已经存在了，添加失败，返回false
			return false;
		}else{
			//如果为空，那么说明该产品名称还不存在，继续判断产品编号
			product = productDao.loadBySn(form.getSn());
			if(null != product){
				return false;
			}else{
				//程序来到这，说明表单提交的产品名称和产品编号都没有人使用，那么设置创建时间和默认库存量后进行保存
				form.setCreate_time(new Date());
				form.setInventory(0);
				productDao.saveProduct(form,productExtend);
				return true;
			}
		}
	}

	public Product getOne(int id) {
		return productDao.loadOne(id);
	}

	public void editProduct(User user,Product form, ProductExtend productExtend) {
		productDao.edit(form,productExtend);
	}

	public List<Product> loadAll() {
		return productDao.loadAll();
	}
	
}
