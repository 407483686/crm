package CRM.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.DocumentaryDao;
import CRM.dao.OrderDao;
import CRM.dao.OutlibDao;
import CRM.dao.ProductDao;
import CRM.entity.Documentary;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Order;
import CRM.entity.Outlib;
import CRM.entity.User;
import CRM.service.OrderService;

@Transactional
public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	private DocumentaryDao documentaryDao;
	private OutlibDao outlibDao;
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setOutlibDao(OutlibDao outlibDao) {
		this.outlibDao = outlibDao;
	}

	public void setDocumentaryDao(DocumentaryDao documentaryDao) {
		this.documentaryDao = documentaryDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(orderDao.findCount(criteria));
		easyUIDatagrid.setRows(orderDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}

	public Order getOne(int id) {
		return orderDao.getOne(id);
	}

	
	
	//新增订单方法
	public void addOrder(User user,Order form, List<Outlib> outlibList,String documentary_id) {
		/*
		 * 一、订单
		 * 	1.设置编号，按照时间生成
		 * 	2.设置关联跟单，根据documentaryid查找
		 * 	3.设置支付状态，默认应该为未支付
		 * 	4.设置录入员，为参数user的账户属性
		 * 	5.设置创建时间
		 * 
		 * 二、出库，一个出库对应一件商品，因此一个订单可能会有多个出库
		 * 	1.循环为每一个出库对象设置订单编号
		 * 	2.循环为每一个出库对象设置出库状态，默认为未处理，等到付款后变成已支付，出库后再由已支付变成已出库
		 * 	3.循环为每一个出库对象设置创建时间
		 * 	4.循环为每一个出库对象设置录入员
		 * 
		 * 三、产品库存，无论出库与否，只要订单中包含了产品，那么库存就要减少
		 * 	1.循环为每一个出库对象设置对应的库存量减少和出库量增加
		 * 
		 * 四、跟单记录，当订单保存成功后，谈判中的跟单要变成已成交的状态
		 * 	1.设置跟单的状态，根据documentaryid查找
		 * */
		//1-1.设置编号
		String sn = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		form.setSn(sn);
		//1-2.设置关联跟单
		Documentary documentary = documentaryDao.getOne(Integer.parseInt(documentary_id));
		form.setDocumentary(documentary);
		//1-3.设置支付状态
		form.setPay_state("未支付");
		//1-4.设置录入员
		form.setEnter(user.getAccounts());
		//1-5.设置创建时间
		form.setCreate_time(new Date());
		
		//循环遍历每一个出库对象
		for (Outlib outlib : outlibList) {
			//2-1.设置订单编号
			outlib.setOrder_sn(sn);
			//2-2.设置出库状态
			outlib.setState("未处理");
			//2-3.设置创建时间
			outlib.setCreate_time(new Date());
			//2-4.设置录入员
			outlib.setEnter(user.getAccounts());
			//2-5.设置库存
			productDao.setKuCun(outlib.getProduct_id(),outlib.getNumber());
		}
		
		//保存
		orderDao.saveOrder(form);
		outlibDao.saveOutlibs(outlibList);
		
		//3-1.设置跟单的状态
		documentaryDao.updateEvolve(documentary_id);
	}

	
	//根据订单编号，在出库记录找到对应的所有出库记录
	public List<Outlib> getOutlibList(String order_sn) {
		return outlibDao.getOutlibListBySn(order_sn);
	}

	//订单由于与多个模块有关联，修改的话会很麻烦，因此只允许修改备注
	public void updateOrder(User user,int id, String details) {
		orderDao.updateOrder(id,details);
	}
	
}
