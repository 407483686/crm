package CRM.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import CRM.dao.OutlibDao;
import CRM.entity.Order;
import CRM.entity.Outlib;

public class OutlibDaoImpl implements OutlibDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	
	//新增订单时保存多条出库记录
	public void saveOutlibs(List<Outlib> outlibList) {
		for (Outlib outlib : outlibList) {
			hibernateTemplate.save(outlib);
		}
	}


	
	
	public int findCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(criteria);
		if(list.size() > 0){
			return list.get(0).intValue();
		}
		
		return 0;
	}

	public List<?> loadByPage(DetachedCriteria criteria, int i, int rows) {
		criteria.setProjection(null);

		List<Outlib> list = (List<Outlib>) hibernateTemplate.findByCriteria(criteria,i,rows);
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}


	
	//根据订单编号加载所有出库记录
	public List<Outlib> getOutlibListBySn(String order_sn) {
		List<Outlib> list = (List<Outlib>) hibernateTemplate.find("from Outlib where order_sn = ?", order_sn);
		return list;
	}


	
	//改变订单编号对应的所有出库记录的状态
	public void changeStateBySn(String order_sn) {
		List<Outlib> list = (List<Outlib>) hibernateTemplate.find("from Outlib where order_sn = ?", order_sn);
		for (Outlib outlib : list) {
			outlib.setState("已支付");
			hibernateTemplate.update(outlib);
		}
	}


	
	//出库
	public int deliver(String[] ids,String accounts) {
		int index = 0;
		for (String id : ids) {
			Outlib outlib = (Outlib) hibernateTemplate.find("from Outlib where id = ?",Integer.parseInt(id)).get(0);
			outlib.setState("已出库");
			outlib.setClerk(accounts);
			outlib.setDispose_time(new Date());
			index += 1;
		}
		return index;
	}
	
}
