package CRM.service;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.User;

public interface OutlibService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	int deliver(User user,String[] ids, String string);

}
