package CRM.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.ClientDao;
import CRM.entity.Client;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.User;
import CRM.service.ClientService;

@Transactional
public class ClientServiceImpl implements ClientService{
	//dao层
	private ClientDao clientDao;

	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	
	//分页查询
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(clientDao.findCount(criteria));
		easyUIDatagrid.setRows(clientDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}

	//详情加载
	public Client getOne(int id) {
		return clientDao.loadOne(id);
	}


	//新增客户
	public boolean addClient(User user,Client form) {
		//新增客户不知道要判断什么，就判断联系人不能相同吧，
		Client client = clientDao.loadByName(form.getName());
		if(client!=null){
			//不为null说明通过联系人的名字获取到了对象，即联系人已经存在
			return false;
		}else{
			//设置录入员和创建时间后传到dao
			form.setCreate_time(new Date());
			form.setEnter(user.getAccounts());
			clientDao.addClient(form);
			
			return true;
		}
	}


	public boolean editClient(User user,Client form) {
		clientDao.editClient(form);
		
		return true;
	}


	//删除客户
	public int removeClient(User user,String[] ids) {
		return clientDao.removeClients(ids);
	}


	
	//查询所有客户
	public List<Client> loadAll() {
		return clientDao.loadAll();
	}
	
}
