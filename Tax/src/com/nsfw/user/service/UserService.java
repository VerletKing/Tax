package com.nsfw.user.service;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import com.core.service.BaseService;
import com.nsfw.user.entity.User;
import com.nsfw.user.entity.UserRole;


public interface UserService extends BaseService<User>{
		
		//�û��б���excel
		public void exportExcel(OutputStream outputStream);

		//�û��б���excel
		public void importExcel(File userExcel, String userExcelFileName);
		
		//�˺���֤
		public List<User> findUserByIdAndAccount(String id, String account);

		public void saveUserAndRole(User user,String ... roleIds);

		public void updateUsereAndRole(User user, String ... roleIds);

		public List<UserRole> findUserRoleById(String id);

		public List<User> findUserByAccountAndPassword(String account, String password);
		
		
		
}
