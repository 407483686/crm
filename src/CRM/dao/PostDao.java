package CRM.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import CRM.entity.Post;
import CRM.entity.QueryCriteria;

public interface PostDao {

	int findCount(DetachedCriteria criteria);

	List<?> loadByPage(DetachedCriteria criteria, int i, int rows);

	Post add(Post form);

	Post loadByName(Post form);

	int updatePost(Post form);

	int removeByIds(String[] ids);

	List<Post> load();



}
