package CRM.tool;

import java.util.Date;

import org.aspectj.lang.JoinPoint;

import CRM.dao.LogDao;
import CRM.entity.Log;
import CRM.entity.User;

public class LogAdvice {
	private LogDao logDao;
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}


	public void addLog(JoinPoint jp){
		//获取切入点方法名和类名
		String className = jp.getTarget().getClass().getSimpleName();
		String methodName = jp.getSignature().getName();
		//根据方法名和类名找到相对应的method和module字段名
		String module = LogMap.moduleMap.get(className);
		String method = LogMap.methodMap.get(methodName);
		
		System.out.println("------------------------------------");
		System.out.println("className = " + className);
		System.out.println("methodName = " + methodName);
		System.out.println("module = " + module);
		System.out.println("method = " + method);
		
		//获取user参数
		User userGet = (User) jp.getArgs()[0];
		//日志的user是登录用户名加真实姓名
		String user = userGet.getAccounts() + "(" + userGet.getStaff().getName() + ")";
		
		//获取ip
		String ip = userGet.getLast_login_ip();
		
		//组装日志对象
		Log log = new Log();
		
		log.setMethod(method);
		log.setModule(module);
		log.setUser(user);
		log.setIp(ip);
		log.setCreate_time(new Date());
		
		logDao.addLog(log);
	}
}
