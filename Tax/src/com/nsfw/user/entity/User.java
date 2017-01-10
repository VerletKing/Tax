package com.nsfw.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class User implements Serializable{
	
	private String id;
	private String dept;
	private String userName;
	private String account;
	private String password;
	
	private String headImg;
	private boolean sex;
	private String state;
	private String mobile;
	private String email;
	private Date birthday;
	private String memo;
	
	private List<UserRole> userRole;
	
	public static String USER_STATE_VALID="1";//有效
	public static String USER_STATE_INVALID="0";//无效
	
	public User() {}
	
	public User(String id, String dept, String userName, String account,
			String password, String headImg, boolean sex, String state,
			String mobile, String email, Date birthday, String memo) {
		super();
		this.id = id;
		this.dept = dept;
		this.userName = userName;
		this.account = account;
		this.password = password;
		this.headImg = headImg;
		this.sex = sex;
		this.state = state;
		this.mobile = mobile;
		this.email = email;
		this.birthday = birthday;
		this.memo = memo;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	
}
