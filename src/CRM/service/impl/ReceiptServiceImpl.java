package CRM.service.impl;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.OrderDao;
import CRM.dao.OutlibDao;
import CRM.dao.ReceiptDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Receipt;
import CRM.entity.User;
import CRM.service.ReceiptService;

@Transactional
public class ReceiptServiceImpl implements ReceiptService {
	private ReceiptDao receiptDao;
	private OrderDao orderDao;
	private OutlibDao outlibDao;
	public void setOutlibDao(OutlibDao outlibDao) {
		this.outlibDao = outlibDao;
	}
	public void setReceiptDao(ReceiptDao receiptDao) {
		this.receiptDao = receiptDao;
	}
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}


	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(receiptDao.findCount(criteria));
		easyUIDatagrid.setRows(receiptDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}


	/*
	 * 1.设置跟单员
	 * 2.设置创建时间
	 * 3、保存
	 * 4.设置订单的收款状态为已支付,根据订单编号查找
	 * 5.设置订单对应的产品的状态，从未处理变成为已支付
	 * */
	public void addReceipt(User user,Receipt form,String userAccount) {
		//1.
		form.setEnter(userAccount);
		//2.
		form.setCreate_time(new Date());
		//3.
		receiptDao.addReceipt(form);
		//4.
		orderDao.changePayStateBySn(form.getOrder_sn());
		//5.
		outlibDao.changeStateBySn(form.getOrder_sn());
	}
	
}
