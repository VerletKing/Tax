package com.core.service.impl;

import java.io.Serializable;
import java.util.List;

import com.core.dao.BaseDao;
import com.core.page.PageResult;
import com.core.service.BaseService;
import com.core.utils.QueryHelper;

public class BaseServiceImpl<T> implements BaseService<T>{

	BaseDao<T> baseDao;
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}
	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}
	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}
	@Override
	public T findById(Serializable id) {
		return baseDao.findById(id);
	}
	@Override
	public List<T> find() {
		return baseDao.find();
	}

	@Override
	@Deprecated 
	public List<T> find(QueryHelper queryHelper) {
		return baseDao.find(queryHelper);
	}

	@Override
	public PageResult find(QueryHelper queryHelper, int pageNo, int pageSize) {
		return  baseDao.find(queryHelper, pageNo, pageSize);
	}
}
