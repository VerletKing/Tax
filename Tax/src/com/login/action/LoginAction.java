package com.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.core.constant.Constant;
import com.nsfw.user.entity.User;
import com.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{

	@Resource
	private UserService userService;
	private User user;
	private String loginInfo;
	
	public String toLoginUI(){
		return "loginUI";
	}
	
	public String login() {
		if (user != null) {
			if (StringUtils.isNotBlank(user.getAccount())
					&& StringUtils.isNotBlank(user.getPassword())) {
				List<User> userList = userService.findUserByAccountAndPassword(
						user.getAccount(), user.getPassword());
				if (userList != null && userList.size() > 0) {
					User nowUser = userList.get(0);
					nowUser.setUserRole(userService.findUserRoleById(nowUser.getId()));
					ActionContext.getContext().getSession().put(Constant.USER, nowUser);
					Log log = LogFactory.getLog(getClass());
					log.info("用户名称：" + user.getUserName() + " 的用户登录了系统。");
					return "home";
				} else {
					loginInfo = "用户名或密码错误。";
				}
			} else {
				loginInfo = "用户名或密码不能为空。";
			}
		} else {
			loginInfo = "用户名或密码不能为空。";
		}
		return "loginUI";
	}
	
	public String loginOut(){ 
		ActionContext.getContext().getSession().remove(Constant.USER);
		return "loginOut";
	}
	
	public String noPermissionUI(){
		return "noPermissionUI";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getLoginInfo() {
		return loginInfo;
	}
}
