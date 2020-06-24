package CRM.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import CRM.dao.PostDao;
import CRM.entity.EasyUIDatagrid;
import CRM.entity.Post;
import CRM.entity.QueryCriteria;
import CRM.entity.User;
import CRM.service.PostService;

@Transactional
public class PostServiceImpl implements PostService {
	private PostDao postDao;
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	public EasyUIDatagrid loadByPage(DetachedCriteria criteria, int rows,int page) {
		EasyUIDatagrid easyUIDatagrid = new EasyUIDatagrid();
		
		easyUIDatagrid.setTotal(postDao.findCount(criteria));
		easyUIDatagrid.setRows(postDao.loadByPage(criteria,rows * (page-1),rows));
		
		return easyUIDatagrid;
	
	}
	public Post addPost(User user,Post form) {
		//后端验证2：职位名称是否存在
		Post post = postDao.loadByName(form);
		if(post == null){
			//为空说明该名称不存在，则执行添加流程
			//获取当前时间，给form设置属性并调用dao层进行添加
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			form.setCreate_time(ts);
			return postDao.add(form);
		}else{
			return null;
		}
	}
	
	//修改角色
	public int editPost(User user,Post form) {
		Post post = postDao.loadByName(form);
		if(post == null){
			return postDao.updatePost(form);
		}else{
			return 0;
		}
	}
	public int removePostByIds(User user,String[] ids) {
		int index = postDao.removeByIds(ids);
		
		return index;
	}
	public List<Post> load() {
		return postDao.load();
	}
}
