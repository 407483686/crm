package CRM.tool;

import java.util.HashMap;
import java.util.Map;

public class LogMap {
	public static Map<String,String> moduleMap = new HashMap<String,String>();
	public static Map<String,String> methodMap = new HashMap<String,String>();
	static{
		moduleMap.put("WorkServiceImpl","办公管理>>工作计划");
		moduleMap.put("InformServiceImpl","办公管理>>通知管理");
		moduleMap.put("LetterServiceImpl","办公管理>>私信收发");
		moduleMap.put("ClientServiceImpl","客户管理>>客户信息");
		moduleMap.put("DocumentaryServiceImpl","客户管理>>跟单记录");
		moduleMap.put("OrderServiceImpl","客户管理>>销售订单");
		moduleMap.put("ProductServiceImpl","仓库管理>>产品信息");
		moduleMap.put("InlibServiceImpl","仓库管理>>入库记录");
		moduleMap.put("OutlibServiceImpl","仓库管理>>出库记录");
		moduleMap.put("ReceiptServiceImpl","财务管理>>收款记录");
		moduleMap.put("UserServiceImpl","人事管理>>登录账号");
		moduleMap.put("StaffServiceImpl","人事管理>>员工档案");
		moduleMap.put("PostServiceImpl","人事管理>>职位部门");
		
		/*
		 * 工作计划
		 * */
		methodMap.put("addWork","新增工作计划");
		methodMap.put("addWorkStage","更新工作计划");
		methodMap.put("finish","完成工作计划");
		methodMap.put("removeWork","作废工作计划");
		
		/*
		 * 通知管理
		 * */
		methodMap.put("addInform","新增通知");
		methodMap.put("editInform","修改通知");
		methodMap.put("removeInform","删除通知");
		
		/*
		 * 私信收发
		 * */
		methodMap.put("addLetter","新增私信");
		methodMap.put("removeLetter","删除私信");
		
		/*
		 * 客户信息
		 * */
		methodMap.put("addClient","新增客户信息");
		methodMap.put("editClient","修改客户信息");
		methodMap.put("removeClient","删除客户信息");
		
		/*
		 * 跟单记录
		 * */
		methodMap.put("addDocumentary","新增跟单记录");
		methodMap.put("editDocumentary","修改跟单记录");
		methodMap.put("removeDocumentary","删除跟单记录");
		
		/*
		 * 销售订单
		 * */
		methodMap.put("addOrder","新增销售订单");
		methodMap.put("updateOrder","修改销售订单");
		
		/*
		 * 产品信息
		 * */
		methodMap.put("addProduct","新增产品信息");
		methodMap.put("editProduct","修改产品信息");
		methodMap.put("removeProduct","删除产品信息");
		
		/*
		 * 入库记录
		 * */
		methodMap.put("addInlib","新增入库记录");
		
		/*
		 * 出库记录
		 * */
		methodMap.put("deliver","新增出库记录");
		
		/*
		 * 收款记录
		 * */
		methodMap.put("addReceipt","新增收款记录");
		
		/*
		 * 登录账号
		 * */
		methodMap.put("addUser","新增账号");
		methodMap.put("editUserState","修改账号状态");
		methodMap.put("editUser","修改账号密码与状态");
		methodMap.put("editUserStaffNameAndUserId","修改账号关联员工档案");
		methodMap.put("editUserPassword","修改账号密码");
		methodMap.put("removeUsersByIds","删除账号");
		
		/*
		 * 员工档案
		 * */
		methodMap.put("addStaff","新增员工档案");
		methodMap.put("editStaff","修改员工档案");
		methodMap.put("removeStaff","删除员工档案");
		
		/*
		 * 职位部门
		 * */
		methodMap.put("addPost","新增职位部门");
		methodMap.put("editPost","修改职位部门");
		methodMap.put("removePostByIds","删除职位部门");
	}
}
