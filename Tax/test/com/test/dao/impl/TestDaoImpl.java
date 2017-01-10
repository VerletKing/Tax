package com.test.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.test.dao.TestDao;
import com.test.entity.Person;

public class TestDaoImpl  extends HibernateDaoSupport implements TestDao{
	
	@Override
	public void save(Person person) {
		getHibernateTemplate().save(person);
	}
	
}
