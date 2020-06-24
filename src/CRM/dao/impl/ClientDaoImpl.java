package CRM.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.ClientDao;
import CRM.entity.Client;
import CRM.entity.Inlib;
import CRM.entity.Staff;

public class ClientDaoImpl implements ClientDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public int findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(criteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		
		return 0;
	}

	public List<?> loadByPage(DetachedCriteria criteria, int i, int rows) {
		criteria.setProjection(null);

		List<Client> list = (List<Client>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}

	public Client loadOne(int id) {
		//如果要加载成json串输出最好不要用load方法，load方法会默认使用延迟加载除非你设置了false，而find则是直接去一二级缓存或者数据库查询
		Client client = (Client) hibernateTemplate.find("from Client where id = ?",id).get(0);
		
		return client;
	}

	
	//添加客户
	public void addClient(Client form) {
		hibernateTemplate.save(form);
	}

	//查找客户的联系人是否已经存在
	public Client loadByName(String name) {
		List<Client> list = (List<Client>) hibernateTemplate.find("from Client where name = ? ", name);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
		
	}

	//编辑客户
	public void editClient(Client form) {
		List<Client> list = (List<Client>) hibernateTemplate.find("from Client where id = ? ", form.getId());
		Client client = list.get(0);
		client.setCompany(form.getCompany());
		client.setSource(form.getSource());
		client.setTel(form.getTel());
		hibernateTemplate.update(client);
	}

	public int removeClients(String[] ids) {
		int index = 0;
		for(String id : ids){
			Client client = hibernateTemplate.load(Client.class,Integer.parseInt(id));
			hibernateTemplate.delete(client);
			index += 1;
		}
		
		return index;
	}

	public List<Client> loadAll() {
		return (List<Client>) hibernateTemplate.find("from Client");
	}
}
