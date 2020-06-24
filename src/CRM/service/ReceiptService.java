package CRM.service;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Receipt;
import CRM.entity.User;

public interface ReceiptService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	void addReceipt(User user,Receipt form, String userAccount);

}
