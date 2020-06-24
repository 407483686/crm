package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Outlib;

public interface OutlibDao {

	void saveOutlibs(List<Outlib> outlibList);

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	List<Outlib> getOutlibListBySn(String order_sn);

	void changeStateBySn(String order_sn);

	int deliver(String[] ids, String accounts);

}
