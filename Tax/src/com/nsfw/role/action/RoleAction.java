package com.nsfw.role.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.core.action.BaseAction;
import com.core.constant.Constant;
import com.core.utils.QueryHelper;
import com.nsfw.role.entity.Role;
import com.nsfw.role.entity.RolePrivilege;
import com.nsfw.role.entity.RolePrivilegeId;
import com.nsfw.role.service.RoleService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class RoleAction extends BaseAction {
	
	@Resource
	RoleService roleService;
	
	Role role;
	String[] privileges;
	
	public String listUI() throws Exception{
		QueryHelper queryHelper = new QueryHelper(Role.class,"r");
		if(role!=null){
			role.setName(URLDecoder.decode(role.getName(),"utf-8"));
			if(StringUtils.isNoneBlank(role.getName())){
				queryHelper.addWhere("r.name like ? ", "%"+role.getName()+"%");
			}
		}
		pageResult = roleService.find(queryHelper,getPageNo(),getPageSize());
		Map<String,String> map = Constant.PRIVILEGE_MAP;
		ActionContext.getContext().getContextMap().put("privilegeMap", map);
		return "listUI";
	}
	
	public String addUI(){
		Map<String,String> map = Constant.PRIVILEGE_MAP;
		ActionContext.getContext().getContextMap().put("privilegeMap", map);
		return "addUI";
	}
	
	public String add(){
		if(role!=null){
			Set<RolePrivilege> set = new HashSet<RolePrivilege>();
			for(String privilege :privileges){
				RolePrivilege rolePrivilege = new RolePrivilege(new RolePrivilegeId(role, privilege));
				set.add(rolePrivilege);
			}
			role.setRolePrivilege(set);
			roleService.save(role);
		}
		return "list";
	}
	
	public String editUI(){
		strTitle = role.getName();
		role = roleService.findById(role.getId());
		Map<String,String> map = Constant.PRIVILEGE_MAP;
		ActionContext.getContext().getContextMap().put("privilegeMap", map);
		Set<RolePrivilege> rolePrivilege = role.getRolePrivilege();
		if(rolePrivilege!=null){
			int i = 0;
			privileges = new String[rolePrivilege.size()];
			for( RolePrivilege index : rolePrivilege){
				privileges[i++] = index.getId().getCode();
			}
		}
		return "editUI";
	}
	
	public String edit(){
		if(role!=null){
			Set<RolePrivilege> set = new HashSet<RolePrivilege>();
			for(String privilege :privileges){
				RolePrivilege rolePrivilege = new RolePrivilege(new RolePrivilegeId(role, privilege));
				set.add(rolePrivilege);
			}
			role.setRolePrivilege(set);
			roleService.update(role);
		}
		return "list";
	}
	
	public String delete(){
		strTitle = role.getName();
		roleService.delete(role.getId());
		return "list";
	}
	
	public String deleteSelected(){
		if(selectedRow!=null){
			strTitle = role.getName();
			for(String id : selectedRow){
				roleService.delete(id);
			}
		}
		return "list";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setPrivileges(String[] privileges) {
		this.privileges = privileges;
	}

	public String[] getPrivileges() {
		return privileges;
	}
	
	
}
