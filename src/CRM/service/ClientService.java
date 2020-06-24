package CRM.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Client;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.User;

public interface ClientService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	Client getOne(int id);

	boolean addClient(User user, Client form);

	boolean editClient(User user,Client form);

	int removeClient(User user,String[] ids);

	List<Client> loadAll();

}
