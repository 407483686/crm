package CRM.service;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Inform;
import CRM.entity.User;

public interface InformService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	Inform getOne(int id);

	void addInform(User user,Inform form);

	void editInform(User user,Inform form);

	int removeInform(User user,String result);

}
