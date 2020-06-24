package CRM.service;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Documentary;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.User;

public interface DocumentaryService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);


	int removeDocumentary(User user,String[] ids);


	Documentary getOne(int id);


	void editDocumentary(User user,Documentary form);


	void addDocumentary(User user,Documentary form, String accounts);

}
