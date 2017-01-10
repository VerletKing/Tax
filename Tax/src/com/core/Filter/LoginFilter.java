package com.core.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.core.constant.Constant;
import com.core.permission.impl.PermissionCheckImpl;
import com.nsfw.user.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String uri = httpRequest.getRequestURI();
		if(!uri.contains("/sys/login_")){
			if(httpRequest.getSession().getAttribute(Constant.USER)!=null){
				if(uri.contains("/nsfw/")){
					User user = (User) httpRequest.getSession().getAttribute(Constant.USER);
					WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(httpRequest.getServletContext());
					PermissionCheckImpl permissionCheck = (PermissionCheckImpl) webApplicationContext.getBean("permissionCheck");
					if(permissionCheck.isAccessible(user,"nsfw")){
						chain.doFilter(httpRequest, httpResponse);
					}else{
						httpResponse.sendRedirect(httpRequest.getContextPath()+"/sys/login_noPermissionUI.action");
					}
				}else{
					chain.doFilter(httpRequest, httpResponse);
				}
			}else{
				httpResponse.sendRedirect(httpRequest.getContextPath()+ "/sys/login_toLoginUI.action");
			}
		}else{
			chain.doFilter(httpRequest, httpResponse);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}
