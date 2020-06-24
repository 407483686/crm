package CRM.service.impl;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.InformDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Inform;
import CRM.entity.User;
import CRM.service.InformService;

@Transactional
public class InformServiceImpl implements InformService {
	private InformDao informDao;

	public void setInformDao(InformDao informDao) {
		this.informDao = informDao;
	}
	
	
	//分页查询
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(informDao.findCount(criteria));
		easyUIDatagrid.setRows(informDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}


	public Inform getOne(int id) {
		return informDao.getOne(id);
	}


	/*
	 * 1.设置staff_id
	 * 2.设置staff_name
	 * 3.设置创建时间
	 * */
	public void addInform(User user,Inform form) {
		//1.
		form.setStaff_id(user.getStaff().getId());
		//2.
		form.setStaff_name(user.getStaff().getName());
		//3.
		form.setCreate_time(new Date());
		
		informDao.add(form);
	}


	public void editInform(User user,Inform form) {
		Inform inform = informDao.getOne(form.getId());
		inform.setTitle(form.getTitle());
		inform.setDetails(form.getDetails());
		informDao.update(inform);
	}


	public int removeInform(User user,String result) {
		int index = 0;
		String[] ids = result.split("丨");
		for (String id : ids) {
			informDao.remove(id);
			index += 1;
		}
		
		return index;
	}
}
