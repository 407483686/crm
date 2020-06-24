package CRM.service;

import java.util.List;

import CRM.entity.AuthGroup;
import CRM.entity.Menu;

public interface MenuService {

	List<Menu> loadMenus(String[] auths);

	String loadAuths(int groupId);

	List<Menu> loadPrivilegeMenus(String[] auths);

	void savePrivilege(String auth_group_id, String privilege_result);


}
