package CRM.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.EasyUIDatagrid;
import CRM.entity.Post;
import CRM.entity.QueryCriteria;
import CRM.entity.User;

public interface PostService {

	EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows, int page);

	Post addPost(User user,Post form);

	int editPost(User user,Post form);

	int removePostByIds(User user,String[] ids);

	List<Post> load();



}
