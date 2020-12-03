package com.myclass.dto;

public class UserDto {
	private int id;
	private String fullname;
	private String email;
	private String roleDescription;
	
	public UserDto() {
		this.fullname = "";
		this.email = "";
		this.roleDescription = "";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
}
