package com.test.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.test.dao.TestDao;
import com.test.entity.Person;
import com.test.service.TestService;

@Service("testService") 
public class TestServiceImpl implements TestService {

	@Resource
	TestDao testDao;
	
	public void print() {
		System.out.println("≤‚ ‘");
	}
	
	public void save(Person person){
		testDao.save(person);
	}

}
