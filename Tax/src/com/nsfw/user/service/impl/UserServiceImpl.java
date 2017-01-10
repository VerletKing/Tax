package com.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.core.service.impl.BaseServiceImpl;
import com.core.utils.ExcelUtils;
import com.nsfw.role.entity.Role;
import com.nsfw.user.dao.UserDao;
import com.nsfw.user.entity.User;
import com.nsfw.user.entity.UserRole;
import com.nsfw.user.entity.UserRoleId;
import com.nsfw.user.service.UserService;

@Service("userSerivce")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	UserDao userDao;
	
	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}
	@Override
	public void delete(Serializable id) {
		if(id!=null){
			userDao.delete(id);
			userDao.deleteUserRoleByUserId(id);
		}
	}

	@Override
	public void exportExcel(OutputStream outputStream)  {
		try {
			List<User> userList = userDao.find();
			ExcelUtils.exportUserExcel(userList, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void importExcel(File userExcel, String userExcelFileName) {
		try {
			FileInputStream inputStream = new FileInputStream(userExcel);
			Workbook workbook = userExcelFileName.matches("^.*\\.(xls)$") ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getPhysicalNumberOfRows()>2){
				User user = null;
				for(int i=2;i<sheet.getPhysicalNumberOfRows();i++){
					user = new User();
					Row row = sheet.getRow(i);
					user.setUserName(row.getCell(0).getStringCellValue());
					user.setAccount(row.getCell(1).getStringCellValue());
					user.setDept(row.getCell(2).getStringCellValue());
					user.setSex(row.getCell(3).getStringCellValue().equals("ÄÐ"));
					
					String mobile = null;
					try{
						mobile = row.getCell(4).getStringCellValue();
					}catch(Exception e){
						Double Dmobile = row.getCell(4).getNumericCellValue();
						mobile = BigDecimal.valueOf(Dmobile).toString();
					}
					user.setMobile(mobile);
					
					user.setEmail(row.getCell(5).getStringCellValue());
					Date date = row.getCell(6).getDateCellValue();
					if(date!=null){
						user.setBirthday(date);
					}
					user.setPassword("123456");
					user.setState(User.USER_STATE_VALID);
					save(user);
				}
				workbook.close();
				inputStream.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findUserByIdAndAccount(String id, String account) {
		return userDao.findUserByIdAndAccount(id, account);
	}

	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		userDao.save(user);
		if(roleIds!=null){
			for(String roleId : roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public void updateUsereAndRole(User user, String... roleIds) {
		userDao.deleteUserRoleByUserId(user.getId());
		userDao.update(user);
		if(roleIds!=null){
			for(String roleId : roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public List<UserRole> findUserRoleById(String id) {
		return userDao.findUserRoleById(id);
	}

	@Override
	public List<User> findUserByAccountAndPassword(String account,
			String password) {
		return userDao.findUserByAccountAndPaaword(account, password);
	}
}



