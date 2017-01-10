package com.nsfw.role.dao.impl;

import org.hibernate.Query;

import com.core.dao.impl.BaseDaoImpl;
import com.nsfw.role.dao.RoleDao;
import com.nsfw.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao  {

	@Override
	public void update(Role entity) {
		Query query = getSession().createQuery("DELETE FROM RolePrivilege WHERE id.role.id = ?");
		query.setString(0, entity.getId());
		query.executeUpdate();
		super.update(entity);
	}
	
}
