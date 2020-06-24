package CRM.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.InlibDao;
import CRM.dao.ProductDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Inlib;
import CRM.entity.User;
import CRM.service.InlibService;

@Transactional
public class InlibServiceImpl implements InlibService {
	//dao层
	private InlibDao inlibDao;
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setInlibDao(InlibDao inlibDao) {
		this.inlibDao = inlibDao;
	}
	
	//新增入库
	public void addInlib(User user,Inlib form,String product_id) {
		inlibDao.addInlib(form,product_id);
		//新增入库之后，要对产品的库存量和入库量进行修改
		productDao.changeInventoryAndInventoryIn(product_id,form.getNumber());
	}

	//分页查询
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(inlibDao.findCount(criteria));
		easyUIDatagrid.setRows(inlibDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}

	
	//根据id加载
	public Inlib getOne(int id) {
		return inlibDao.getOne(id);
	}
	
	
}
