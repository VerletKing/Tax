package com.core.dao;

import java.io.Serializable;
import java.util.List;

import com.core.page.PageResult;
import com.core.utils.QueryHelper;

public interface BaseDao<T> {
	
	//����
	public void save(T entity);
	
	//ɾ��
	public void delete(Serializable id);
	
	//����
	public void update(T entity);
	
	//����id��ѯ
	public T findById(Serializable id);
	
	//��ѯȫ��
	public List<T> find();
	
	//�б��ѯ
	public List<T> find(QueryHelper queryHelper);
	
	public PageResult find(QueryHelper queryHelper,  int pageNo, int pageSize);
}
