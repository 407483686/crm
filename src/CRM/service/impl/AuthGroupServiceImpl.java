package CRM.service.impl;

import org.hibernate.criterion.DetachedCriteria;

import CRM.dao.AuthGroupDao;
import CRM.entity.EasyUIDatagrid;
import CRM.service.AuthGroupService;

public class AuthGroupServiceImpl implements AuthGroupService {
	private AuthGroupDao authGroupDao;
	public void setAuthGroupDao(AuthGroupDao authGroupDao) {
		this.authGroupDao = authGroupDao;
	}
	
	//分页查询
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(authGroupDao.findCount(criteria));
		easyUIDatagrid.setRows(authGroupDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}
}
