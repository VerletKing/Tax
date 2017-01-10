package com.test.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.test.service.TestService;

@SuppressWarnings("serial")
public class TestAction extends ActionSupport{
	
	@Resource
	TestService testService;
	
	@Override
	public String execute() throws Exception {
		testService.print();
		return SUCCESS;
	}
	
}
