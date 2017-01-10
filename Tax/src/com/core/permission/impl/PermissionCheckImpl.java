package com.core.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import com.core.permission.PermissionCheck;
import com.nsfw.role.entity.Role;
import com.nsfw.role.entity.RolePrivilege;
import com.nsfw.user.entity.User;
import com.nsfw.user.entity.UserRole;
import com.nsfw.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {
	
	@Resource
	UserService userService;

	public boolean isAccessible(User user, String code) {
		List<UserRole> list = user.getUserRole();
		
		if(list==null){
			list = userService.findUserRoleById(user.getId());
		}
		
		if(list!=null && list.size()>0){
			for(UserRole userRole : list){
				Role role = userRole.getId().getRole();
				for(RolePrivilege rolePrivilege : role.getRolePrivilege()){
					if(rolePrivilege.getId().getCode().equals(code)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
