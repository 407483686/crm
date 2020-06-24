package CRM.service;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Letter;
import CRM.entity.User;

public interface LetterService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	Letter getOne(int id);

	void setRead(int id);

	void addLetter(User user, Letter form);


	int removeLetter(User user, String[] ids);

}
