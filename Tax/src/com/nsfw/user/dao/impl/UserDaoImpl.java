package com.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.core.dao.impl.BaseDaoImpl;
import com.nsfw.user.dao.UserDao;
import com.nsfw.user.entity.User;
import com.nsfw.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User>  implements UserDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByIdAndAccount(String id, String account) {
		String hql = "FROM User WHERE account = ?";
		if(StringUtils.isNotBlank(id)){
			hql += " AND id != ?";
		}
		Query query = getSession().createQuery(hql);
		query.setString(0, account);
		if(StringUtils.isNotBlank(id)){
			query.setString(1, id);
		}
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}

	@Override
	public void deleteUserRoleByUserId(Serializable userId) {
		Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId = ? ");
		query.setParameter(0, userId);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findUserRoleById(String id) {
		Query query = getSession().createQuery("FROM UserRole WHERE id.userId = ? ");
		query.setString(0, id);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByAccountAndPaaword(String account,
			String password) {
		Query query = getSession().createQuery("FROM User WHERE account=? AND password=?");
		query.setString(0, account);
		query.setString(1, password);
		return query.list();
	}

}
