package com.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.entity.Person;
import com.test.service.TestService;

public class Tests {
	
	ApplicationContext ac;
	
	@Before
	public void load(){
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testSpring() {
		TestService testService = (TestService) ac.getBean("testService");
		testService.print();
	}
	
	@Test
	public void TestHIbernate(){
		TestService testService = (TestService) ac.getBean("testService");
		testService.save(new Person("Эѕеп0"));
	}

}
