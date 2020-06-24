package CRM.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import CRM.dao.AuthGroupAccessDao;
import CRM.dao.AuthGroupDao;
import CRM.dao.MenuDao;
import CRM.entity.Attributes;
import CRM.entity.AuthGroup;
import CRM.entity.Menu;
import CRM.service.MenuService;

@Transactional
public class MenuServiceImpl implements MenuService {
	//持久层对象
	private MenuDao menuDao;
	private AuthGroupAccessDao authGroupAccessDao;
	private AuthGroupDao authGroupDao;
	
	public void setAuthGroupAccessDao(AuthGroupAccessDao authGroupAccessDao) {
		this.authGroupAccessDao = authGroupAccessDao;
	}

	public void setAuthGroupDao(AuthGroupDao authGroupDao) {
		this.authGroupDao = authGroupDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	//加载菜单方法
	public List<Menu> loadMenus(String[] auths) {
		//加载父菜单
		List<Menu> list = menuDao.loadByPid(0);
		for (Menu menu : list) {
			//加载父菜单对应的所有子菜单
			List<Menu> listChildren = menuDao.loadByPid(menu.getId());
			//这个才是最终加载的子菜单
			List<Menu> listChildrenAdd = new ArrayList<Menu>();
			
			
			//封装Attributes
			for (Menu children : listChildren) {
				Attributes attr = new Attributes();
				attr.setHref(children.getHref());
				children.setAttributes(attr);

				//判断授权
				//当子菜单的id和授权中的id相同时，添加子菜单到父菜单中
				for (String auth : auths) {
					if(children.getId() == Integer.parseInt(auth)){
						listChildrenAdd.add(children);
					}
				}
			}
			
			
			
			menu.setChildren(listChildrenAdd);
		}
		
		return list;
	}

	
	
	public String loadAuths(int groupId) {
		return authGroupDao.loadAuths(groupId);
	}

	public List<Menu> loadPrivilegeMenus(String[] auths) {
		//加载父菜单
		List<Menu> list = menuDao.loadByPid(0);
		for (Menu menu : list) {
			//加载父菜单对应的所有子菜单
			List<Menu> listChildren = menuDao.loadByPid(menu.getId());
			
			
			//封装Attributes
			for (Menu children : listChildren) {
				Attributes attr = new Attributes();
				attr.setHref(children.getHref());
				children.setAttributes(attr);

				//判断授权
				//当子菜单的id和授权中的id相同时，把子菜单的check设置为true
				for (String auth : auths) {
					if(children.getId() == Integer.parseInt(auth)){
						children.setChecked(true);
					}
				}
			}
			
			
			
			menu.setChildren(listChildren);
		}
		
		return list;
	}

	public void savePrivilege(String auth_group_id, String privilege_result) {
		String auths = "";
		String[] privilege_results = privilege_result.split("丨");
		//加载父菜单
		List<Menu> list = menuDao.loadAll();
		for (Menu menu : list) {
			for (String p_result : privilege_results) {
				if(menu.getText().equals(p_result)){
					auths += menu.getId() + ",";
				}
			}
		}
		
		authGroupDao.saveAuths(auth_group_id,auths);
	}

	
	
	
	
}
