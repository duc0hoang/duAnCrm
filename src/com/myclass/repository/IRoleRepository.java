package com.myclass.repository;

import java.util.List;

import com.myclass.entity.Role;

public interface IRoleRepository {
	List<Role> getAllRole();

	void removeRoleById(int id);

	Role getRoleById(int id);

	void addNewRole(Role role);

	void updateRoleById(Role role, int id);
}
