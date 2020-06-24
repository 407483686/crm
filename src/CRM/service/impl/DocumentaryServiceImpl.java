package CRM.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.DocumentaryDao;
import CRM.entity.Documentary;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.User;
import CRM.service.DocumentaryService;


@Transactional
public class DocumentaryServiceImpl implements DocumentaryService {
	//dao层
	private DocumentaryDao documentaryDao;
	public void setDocumentaryDao(DocumentaryDao documentaryDao) {
		this.documentaryDao = documentaryDao;
	}
	
	//分页查询
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(documentaryDao.findCount(criteria));
		easyUIDatagrid.setRows(documentaryDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}

	
	
	//删除
	public int removeDocumentary(User user,String[] ids) {
		return documentaryDao.removeDocumentary(ids);
	}

	
	//获取一个对象
	public Documentary getOne(int id) {
		return documentaryDao.getOne(id);
	}

	
	//修改
	public void editDocumentary(User user,Documentary form) {
		documentaryDao.edit(form);
	}

	public void addDocumentary(User user,Documentary form, String accounts) {
		/*
		 * 1.设置跟单员，从action传递过来
		 * 2.设置创建时间
		 * 3.设置编号，使用创建时间转为特定格式
		 * */
		form.setEnter(accounts);
		form.setCreate_time(new Date());
		form.setSn(new SimpleDateFormat("yyyyMMddHHmm").format(new Date()));
		
		documentaryDao.add(form);
	}
}
