package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Client;

public interface ClientDao {

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	Client loadOne(int id);

	void addClient(Client form);

	Client loadByName(String name);

	void editClient(Client form);

	int removeClients(String[] ids);

	List<Client> loadAll();

}
