package CRM.service;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Inlib;
import CRM.entity.User;

public interface InlibService {

	void addInlib(User user,Inlib form, String product_id);

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	Inlib getOne(int id);

}
