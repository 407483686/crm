package CRM.service.impl;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.StaffDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Extend;
import CRM.entity.Staff;
import CRM.entity.User;
import CRM.service.StaffService;

@Transactional
public class StaffServiceImpl implements StaffService {
	//dao层对象
	private StaffDao staffDao;
	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}

	//分页条件查询员工
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(staffDao.findCount(criteria));
		easyUIDatagrid.setRows(staffDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}

	
	//添加员工档案和拓展资料
	public boolean addStaff(User user,Staff form,Extend extend) {
		/*
		 * 有两个需要校验的地方：
		 * 	1.校验员工编号是否已经存在
		 * 	2.校验身份证是否已经存在
		 * 
		 * 保存是级联保存
		 * */
		//1.校验员工编号
		Staff staff = staffDao.loadByNumber(form.getNumber());
		if(null != staff){
			//如果不为空，那么说明该员工编号已经存在了，添加失败，返回false
			return false;
		}else{
			//如果为空，那么说明该员工编号还不存在，继续判断身份证
			staff = staffDao.loadByIdCard(form.getId_card());
			if(null != staff){
				return false;
			}else{
				//程序来到这，说明表单提交的身份证和员工编号都没有人使用，那么设置创建时间后进行级联保存
				form.setCreate_time(new Date());
				staffDao.saveStaff(form,extend);
				return true;
			}
		}
	}

	public int removeStaff(User user,String[] ids) {
		return staffDao.removeStaffs(ids);
	}

	public Staff getOne(int id) {
		return staffDao.loadOne(id);
	}

	public void editStaff(User user,Staff form, Extend extend, String group_id) {
		staffDao.edit(form,extend,group_id);
	}


}
