package CRM.service;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;

public interface LogService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

}
