package com.nsfw.role.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.core.service.impl.BaseServiceImpl;
import com.nsfw.role.dao.RoleDao;
import com.nsfw.role.entity.Role;
import com.nsfw.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{
	
	RoleDao roleDao;

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}
}
