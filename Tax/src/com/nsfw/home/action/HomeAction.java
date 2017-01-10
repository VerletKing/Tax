package com.nsfw.home.action;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class HomeAction extends ActionSupport{
	
	public String frame(){
		return "frame";
	}
	
	public String left(){
		return "left";
	}
	
	public String top(){
		return "top";
	}
}
