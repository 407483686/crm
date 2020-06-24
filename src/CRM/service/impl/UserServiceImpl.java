package CRM.service.impl;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.StaffDao;
import CRM.dao.UserDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Staff;
import CRM.entity.User;
import CRM.service.UserService;
import CRM.tool.MD5Utils;

@Transactional
public class UserServiceImpl implements UserService {
	//DAO
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	private StaffDao staffDao;
	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	//分页条件查询用户
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(userDao.findCount(criteria));
		easyUIDatagrid.setRows(userDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}
	
	//在删除用户之前调用，清空用户的staff
	
	//删除用户
	public int removeUsersByIds(User userLogin,String[] ids,String[] staff_ids_arr) {
		int index = 0;
		for (String id : ids) {
			userDao.removeByIds(id);
			index += 1;
		}
		//删除之后，把对应的有关联的档案的，那些关联档案全部都userid清除掉
		staffDao.removeUserId(staff_ids_arr);
		
		return index;
	}


	//新增用户
	public User addUser(User userLogin,User form,String staff_id) {
		//后端验证2：用户名是否存在
		User user = userDao.loadByName(form);
		if(user == null){
			//为空说明该名不存在，则执行添加流程
			//1.给用户的密码加密
			form.setPassword(MD5Utils.getMD5(form.getPassword()));
			//2.设置用户的创建时间
			form.setCreate_time(new Date());
			
			//3.设置用户关联的员工档案，如果不为空的话
			if(!staff_id.equals("")){
				Staff staff = staffDao.loadOne(Integer.parseInt(staff_id));
				form.setStaff(staff);
			}
			
			//4.执行dao的添加方法
			User userSave = userDao.add(form);
			
			//5如果有关联的员工档案，给员工档案的userid设置一下
			if(!staff_id.equals("")){
				staffDao.setUserId(staff_id,userSave);
			}
			
			return userSave;
		}else{
			return null;
		}
	}

	
	//修改用户密码和状态
	public User editUser(User userLogin,User form){
		User user = userDao.loadById(form);
		//在比较表单的密码和数据库的密码之前，必须先把表单的密码进行加密
		form.setPassword(MD5Utils.getMD5(form.getPassword()));
		if(user.getPassword().equals(form.getPassword())){
			//如果表单提交的密码和数据库中的密码一样，就返回null
			return null;
		}else{
			//如果不一样，则后执行修改操作
			return userDao.updatePasswordAndState(form);
		}

	}

	public User editUserState(User userLogin,User form){
		return userDao.updateState(form);
	}

	//根据用户名和密码查找用户是否存在
	public User login(User form) {
		form.setPassword(MD5Utils.getMD5(form.getPassword()));
		return userDao.loadByNameAndPassword(form);
	}

	//登录调用这个方法
	public void update(User user,String ip) {
		/*
		 * 1.更新用户的登录次数
		 * 2.更新用户的最后登录时间
		 * 3.更新用户的最后登录ip	
		 * */
		user.setLogin_count(user.getLogin_count() + 1);
		user.setLast_login_time(new Date());
		user.setLast_login_ip(ip);
		userDao.update(user);
	}

	public User editUserPassword(User userLogin,User form) {
		User user = userDao.loadById(form);
		//在比较表单的密码和数据库的密码之前，必须先把表单的密码进行加密
		form.setPassword(MD5Utils.getMD5(form.getPassword()));
		if(user.getPassword().equals(form.getPassword())){
			//如果表单提交的密码和数据库中的密码一样，就返回null
			return null;
		}else{
			return userDao.updatePassword(form);
		}
	}

	
	
	//修改密码或者状态之后,如果判断staff_id是纯数字，说明用户有选择，那么会调用这个方法，用来修改关联员工档案
	//1.把原来的员工档案的userid清除，可以通过userid先查找
	//2.把新的员工档案的userid设置，可以通过目标staff_id查找
	public void editUserStaffNameAndUserId(User userLogin,User user,String staff_id) {
		staffDao.removeOneUserId(user.getId());
		User userNew = userDao.loadById(user);
		staffDao.setUserId(staff_id,userNew);
		Staff staff = staffDao.loadOne(Integer.parseInt(staff_id));
		userNew.setStaff(staff);
		userDao.update(userNew);
	}
	
	
}
