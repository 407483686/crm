package CRM.service;

import java.text.ParseException;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.User;
import CRM.entity.Work;

public interface WorkService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	void addWork( User user, Work form,String state_date_string, String end_date_string) throws ParseException;

	Work getOne(int id);

	void addWorkStage(User user,int id, String stage_title);

	void finish(User user, int id);

	int removeWork(User user, String[] ids);

	void addAllo(Work form, User user, String state_date_string,
			String end_date_string) throws ParseException;

}
