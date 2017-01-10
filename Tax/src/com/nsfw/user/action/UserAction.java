package com.nsfw.user.action;

import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.core.action.BaseAction;
import com.core.utils.QueryHelper;
import com.nsfw.role.service.RoleService;
import com.nsfw.user.entity.User;
import com.nsfw.user.entity.UserRole;
import com.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class UserAction extends BaseAction{
	
	@Resource
	UserService userService;
	@Resource
	RoleService roleService;
	
	File headImg;
	String heagImgContentType;
	String headImgFileName;
	File userExcel;
	String userExcelContentType;
	String userExcelFileName;
	String[] roleId;
	
	
	public String[] getRoleId() {
		return roleId;
	}
	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelContentType() {
		return userExcelContentType;
	}
	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	public File getHeadImg() {
		return headImg;
	}
	public String getHeagImgContentType() {
		return heagImgContentType;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public void setHeagImgContentType(String heagImgContentType) {
		this.heagImgContentType = heagImgContentType;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	
	User user;
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
	public String listUI() throws Exception{
		QueryHelper queryHelper = new QueryHelper(User.class,"u");
		if(user!=null){
			if(user.getUserName()!=null){
				user.setUserName(URLDecoder.decode(user.getUserName(), "utf-8"));
				if(StringUtils.isNotBlank(user.getUserName())){
					queryHelper.addWhere("u.userName like ?", "%"+user.getUserName()+"%");
				}
			}
		}
		pageResult = userService.find(queryHelper,getPageNo(),getPageSize());
		return "listUI";
	}
	
	public String addUI(){
		ActionContext.getContext().getContextMap().put("roleList", roleService.find());
		return "addUI";
	}
	
	public String add(){
		
		try {
			if (headImg != null) {
				String imgPath = ServletActionContext.getServletContext()
						.getRealPath("upload/user");
				String name = UUID.randomUUID().toString().replaceAll("-", "")
						+ headImgFileName.substring(headImgFileName
								.lastIndexOf("."));

				FileUtils.copyFile(headImg, new File(imgPath,name));
				user.setHeadImg("user/"+name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		userService.saveUserAndRole(user, roleId);
		return "list";
	}
	
	public String editUI(){
		strTitle = user.getUserName();
		user = userService.findById(user.getId());
		List<UserRole> userRoleList = userService.findUserRoleById(user.getId());
		if(userRoleList!=null){
			roleId = new String[userRoleList.size()];
			for(int i=0;i<userRoleList.size();i++){
				roleId[i] = userRoleList.get(i).getId().getRole().getId();
			}
		}
		ActionContext.getContext().getContextMap().put("roleList", roleService.find());
		return "editUI";
	}
	
	public String edit(){
		try {
			if(headImg!=null){
				String imgPath = ServletActionContext.getServletContext().getRealPath("upload/user");
				String name = UUID.randomUUID().toString().replaceAll("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
				FileUtils.copyFile(headImg, new File(imgPath,name));
				
				new File(ServletActionContext.getServletContext().getRealPath("upload/"+user.getHeadImg())).delete();
				user.setHeadImg("user/"+name);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		userService.updateUsereAndRole(user,roleId);
		return "list";
	}
	
	public String delete(){
		strTitle = user.getUserName();
		userService.delete(user.getId());
		return "list";
	}
	
	public String deleteSelected(){
		if(selectedRow!=null){
			strTitle = user.getUserName();
			for(String id : selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	
	public void exportExcel(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-execl");
			response.setHeader("content-Disposition", "attachment;filename="+new String("用户列表.xls".getBytes(),"iso-8859-1"));
			OutputStream outputStream = response.getOutputStream();
			userService.exportExcel(outputStream);
			if(outputStream != null){
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String importExcel(){
		if(userExcel!=null){
			if(userExcelFileName.matches("^.*\\.((xls)|(xlsx))$")){
				userService.importExcel(userExcel,userExcelFileName);
			}
		}
		return "list";
	}
	
	public void verification(){
		try {
			if(user!=null && StringUtils.isNotBlank(user.getAccount())){
				List<User> list = userService.findUserByIdAndAccount(user.getId(),user.getAccount());
				String flag = "true";
				if(list!=null && list.size()>0){
					flag = "float";
				}
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(flag.getBytes());
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
