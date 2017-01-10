package com.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import com.core.dao.BaseDao;
import com.nsfw.user.entity.User;
import com.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	List<User> findUserByIdAndAccount(String id, String account);

	void saveUserRole(UserRole userRole);

	void deleteUserRoleByUserId(Serializable userId);

	List<UserRole> findUserRoleById(String id);

	List<User> findUserByAccountAndPaaword(String account, String password);
	
}
