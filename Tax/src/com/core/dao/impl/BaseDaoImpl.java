package com.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.core.dao.BaseDao;
import com.core.page.PageResult;
import com.core.utils.QueryHelper;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl(){
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(findById(id));
	}

	@Override
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		T t =getHibernateTemplate().get(clazz, id);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find() {
		Query query = getSession().createQuery("FROM "+clazz.getSimpleName());
		return query.list();
	}

	@Override
	public List<T> find(QueryHelper queryHelper) {
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object> params = queryHelper.getParameters();
		if(params !=null){
			for(int i=0;i<params.size();i++){
				query.setParameter(i, params.get(i));
			}
		}
		return query.list();
	}

	@Override
	public PageResult find(QueryHelper queryHelper, int pageNo, int pageSize) {
		Query query = getSession().createQuery(queryHelper.getQueryListHql());
		List<Object> params = queryHelper.getParameters();
		if(params !=null){
			for(int i=0;i<params.size();i++){
				query.setParameter(i, params.get(i));
			}
		}
		if(pageNo<1)
			pageNo=1;
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageSize);
		List items = query.list();
		
		Query queryCount = getSession().createQuery(queryHelper.getQueryCountHql());
		if(params !=null){
			for(int i=0;i<params.size();i++){
				queryCount.setParameter(i, params.get(i));
			}
		}
		long totalCount =  (Long) queryCount.uniqueResult();
		
		PageResult pageResult = new PageResult(totalCount,pageNo,pageSize,items);
		return pageResult;
	}
	
}
