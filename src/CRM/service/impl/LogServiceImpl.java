package CRM.service.impl;

import org.hibernate.criterion.DetachedCriteria;

import CRM.dao.LogDao;
import CRM.entity.EasyUIDatagrid;
import CRM.service.LogService;

public class LogServiceImpl implements LogService {
	private LogDao logDao;
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(logDao.findCount(criteria));
		easyUIDatagrid.setRows(logDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;	
	}
	
}
