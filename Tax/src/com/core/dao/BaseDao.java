package com.core.dao;

import java.io.Serializable;
import java.util.List;

import com.core.page.PageResult;
import com.core.utils.QueryHelper;

public interface BaseDao<T> {
	
	//增加
	public void save(T entity);
	
	//删除
	public void delete(Serializable id);
	
	//更新
	public void update(T entity);
	
	//根据id查询
	public T findById(Serializable id);
	
	//查询全部
	public List<T> find();
	
	//列表查询
	public List<T> find(QueryHelper queryHelper);
	
	public PageResult find(QueryHelper queryHelper,  int pageNo, int pageSize);
}
