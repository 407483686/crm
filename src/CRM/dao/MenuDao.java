package CRM.dao;

import java.util.List;

import CRM.entity.Menu;

public interface MenuDao {

	List<Menu> loadByPid(int i);

	List<Menu> loadAll();

}
