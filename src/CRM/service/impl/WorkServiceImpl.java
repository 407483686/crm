package CRM.service.impl;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.WorkDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.User;
import CRM.entity.Work;
import CRM.entity.WorkStage;
import CRM.service.WorkService;
import CRM.tool.TimeFormat;

@Transactional
public class WorkServiceImpl implements WorkService {
	//dao
	private WorkDao workDao;
	public void setWorkDao(WorkDao workDao) {
		this.workDao = workDao;
	}

	//分页查询
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,
			int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(workDao.findCount(criteria));
		easyUIDatagrid.setRows(workDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	}

	
	/*
	 * 1.设置初始任务进度为：创建工作任务
	 * 2.设置初始状态为：进行中
	 * 3.设置实施人id为：当前登陆用户id
	 * 4.设置实施人名字为：当前登陆用户关联的员工的名字
	 * 5.设置录入员为：当前登陆用户关联的员工的名字,同时设置录入员的id
	 * 6.设置开始和结束时间，用字符串去转换
	 * 7.设置创建时间
	 * 8.设置附属表的工作阶段名称为：创建工作任务
	 * 9.设置附属表的创建时间
	 * 10.设置附属表的外键
	 * */
	public void addWork(User user,Work form,String state_date_string, String end_date_string) throws ParseException {
		//1.
		form.setStage("创建工作任务");
		//2.
		form.setState("进行中");
		//3.
		form.setStaff_id(user.getStaff().getId());
		//4.
		form.setStaff_name(user.getStaff().getName());
		//5.
		form.setEnter(user.getStaff().getName());
		form.setEnter_id(user.getStaff().getId());
		//6.
		form.setState_date(TimeFormat.changeToDate(state_date_string));
		form.setEnd_date(TimeFormat.changeToDate(end_date_string));
		//7.
		form.setCreate_time(new Date());
		//8.9.10.
		WorkStage ws = new WorkStage();
		ws.setTitle("创建工作任务");
		ws.setCreate_time(new Date());
		form.getWorkStages().add(ws);
		//保存
		workDao.addWork(form);
	}

	public Work getOne(int id) {
		return workDao.getOne(id);
	}

	/*
	 * 新增工作阶段
	 * 	1、利用id获取到工作对象
	 * 	2.创建新的工作阶段对象
	 * 	3.给工作阶段对象设置创建时间和名称
	 * 	4.让工作对象和工作阶段对象相互关联
	 * 	5.工作对象本身的工作阶段也要设置
	 * 	6.保存
	 * */
	public void addWorkStage(User user,int id, String stage_title) {
		//1.
		Work work = workDao.getOne(id);
		//2.
		WorkStage ws = new WorkStage();
		//3.
		ws.setCreate_time(new Date());
		ws.setTitle(stage_title);
		//4.
		work.getWorkStages().add(ws);
		//5.
		work.setStage(stage_title);
		//6.
		workDao.addWork(work);
	}

	
	/*	1、利用id获取到工作对象
	 * 	2.创建新的工作阶段对象
	 * 	3.把工作对象的stage设置为工作计划完成
	 * 	4.把工作对象的状态设置为已完成
	 * 	5.给新建的工作阶段对象设置创建时间和title，title同样是工作计划完成
	 * 	6.让工作对象和工作阶段对象相互关联
	 * 	7.保存
	 * */
	public void finish(User user,int id) {
		//1.
		Work work = workDao.getOne(id);
		//2.
		WorkStage ws = new WorkStage();
		//3.
		work.setStage("工作计划完成");
		//4.
		work.setState("已完成");
		//5.
		ws.setCreate_time(new Date());
		ws.setTitle("工作计划完成");
		//6.
		work.getWorkStages().add(ws);
		//7.保存
		workDao.addWork(work);
	}

	//根据ids获取对象，并设置state为已作废 
	public int removeWork(User user,String[] ids) {
		int index = 0;
		for (String id : ids) {
			Work work = workDao.getOne(Integer.parseInt(id));
			work.setState("已作废");
			workDao.addWork(work);
			index += 1;
		}
			
		return index;
	}

	
	
	/*
	 * 1.设置初始任务进度为：创建工作任务
	 * 2.设置初始状态为：进行中
	 * 其实3和4不用设置，已经存在表单中了，因为比原来的多了两个输入项
	 * 3.设置实施人id为：表单提交的staff_id
	 * 4.设置实施人名字为：表单提交的staff_name
	 * 5.设置录入员为：当前登陆用户关联的员工的名字,同时设置录入员的id
	 * 6.设置开始和结束时间，用字符串去转换
	 * 7.设置创建时间
	 * 8.设置附属表的工作阶段名称为：创建工作任务
	 * 9.设置附属表的创建时间
	 * 10.设置附属表的外键
	 * */
	public void addAllo(Work form, User user, String state_date_string,
			String end_date_string) throws ParseException {
		//1.
		form.setStage("创建工作任务");
		//2.
		form.setState("进行中");
		//5.
		form.setEnter(user.getStaff().getName());
		form.setEnter_id(user.getStaff().getId());
		//6.
		form.setState_date(TimeFormat.changeToDate(state_date_string));
		form.setEnd_date(TimeFormat.changeToDate(end_date_string));
		//7.
		form.setCreate_time(new Date());
		//8.9.10.
		WorkStage ws = new WorkStage();
		ws.setTitle("创建工作任务");
		ws.setCreate_time(new Date());
		form.getWorkStages().add(ws);
		//保存
		workDao.addWork(form);
	}

}
