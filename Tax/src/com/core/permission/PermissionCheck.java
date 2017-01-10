package com.core.permission;

import com.nsfw.user.entity.User;

public interface PermissionCheck {
	public boolean isAccessible(User user, String code);
}
