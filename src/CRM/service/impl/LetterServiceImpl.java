package CRM.service.impl;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.LetterDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Letter;
import CRM.entity.User;
import CRM.service.LetterService;

@Transactional
public class LetterServiceImpl implements LetterService {
	private LetterDao letterDao;

	public void setLetterDao(LetterDao letterDao) {
		this.letterDao = letterDao;
	}
	
	//分页查询
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(letterDao.findCount(criteria));
		easyUIDatagrid.setRows(letterDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}

	public Letter getOne(int id) {
		return letterDao.getOne(id);
	}

	public void setRead(int id) {
		letterDao.setRead(id);
	}

	/*
	 * 1.message、staff_id、staff_name、都在form
	 * 2.send_id、send_name都在user
	 * 3.设置isRead为未读，和设置创建时间
	 * 
	 * */
	public void addLetter(User user, Letter form) {
		form.setSend_id(user.getStaff().getId());
		form.setSend_name(user.getStaff().getName());
		form.setIsRead("未读");
		form.setCreate_time(new Date());
		letterDao.add(form);
	}

	public int removeLetter(User user,String[] ids) {
		return letterDao.removeLetter(ids);
	}
}
