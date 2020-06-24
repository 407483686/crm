package CRM.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import CRM.entity.AuthGroup;
import CRM.entity.Menu;
import CRM.entity.User;
import CRM.service.MenuService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MenuAction  extends ActionSupport implements ModelDriven<Menu>{
	//模型驱动
	Menu form = new Menu();
	public Menu getModel() {
		return form;
	}
	
	//属性驱动
	//授权组id
	private String auth_group_id;
	public void setAuth_group_id(String auth_group_id) {
		this.auth_group_id = auth_group_id;
	}
	//授权结果
	private String privilege_result;
	public void setPrivilege_result(String privilege_result) {
		this.privilege_result = privilege_result;
	}

	//业务层对象
	private MenuService menuService;
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	//加载菜单方法
	public String loadMenus() throws IOException{
		//获取登陆账号
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//获取账号对应权限
		String authstr = menuService.loadAuths(user.getStaff().getAuthGroupAccess().getGroupId());
		String[] auths = authstr.split(",");
		
		
		//获取菜单列表
		List<Menu> list = menuService.loadMenus(auths);
		
		
		Gson gson = new Gson();
		String data = gson.toJson(list);
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		
		return NONE;
	}
	
	//加载授权
	public String showPrivilege() throws IOException{
		String authstr = menuService.loadAuths(Integer.parseInt(auth_group_id));
		String[] auths = authstr.split(",");
		
		List<Menu> list = menuService.loadPrivilegeMenus(auths);
		
		
		Gson gson = new Gson();
		String data = gson.toJson(list);
		
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(data);
		return NONE;
	}

	//保存授权
	public String savePrivilege() throws IOException{
		menuService.savePrivilege(auth_group_id,privilege_result);
		
		
		ServletActionContext.getResponse().getWriter().print(1);
		return NONE;
	}
}
