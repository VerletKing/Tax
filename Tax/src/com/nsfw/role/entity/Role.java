package com.nsfw.role.entity;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("serial")
public class Role implements Serializable{
	private String id;
	private String name;
	private String state;
	private Set<RolePrivilege> rolePrivilege;
	
	public static String ROLE_STATE_VALID="1";//有效
	public static String ROLE_STATE_INVALID="0";//无效
	
	public Role() {
	}
	
	public Role(String id){
		this.id=id;
	}
	public Role(String id, String name, String state,
			Set<RolePrivilege> rolePrivilege) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.rolePrivilege = rolePrivilege;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<RolePrivilege> getRolePrivilege() {
		return rolePrivilege;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setRolePrivilege(Set<RolePrivilege> rolePrivilege) {
		this.rolePrivilege = rolePrivilege;
	}
	
}
