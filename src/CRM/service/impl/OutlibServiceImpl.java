package CRM.service.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.OutlibDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.User;
import CRM.service.OutlibService;

@Transactional
public class OutlibServiceImpl implements OutlibService {
	private OutlibDao outlibDao;

	public void setOutlibDao(OutlibDao outlibDao) {
		this.outlibDao = outlibDao;
	}

	
	
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(outlibDao.findCount(criteria));
		easyUIDatagrid.setRows(outlibDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}



	
	
	/*
	 * 一、出库
	 * 	1.对每一个出库记录对象的状态进行设置，从已支付，变为已出库
	 * 	2.对每一个出库记录设置出货员
	 * */
	public int deliver(User user,String[] ids,String accounts) {
		return outlibDao.deliver(ids,accounts);
	}

	
}
