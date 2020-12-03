package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.entity.Role;
import com.myclass.repository.IRoleRepository;
@Component
public class RoleService implements IRoleService{
	IRoleRepository roleRepository;
	
	public RoleService(IRoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	@Override
	public List<Role> getAllRole() {
		return roleRepository.getAllRole();
	}

	@Override
	public void removeRoleById(int id) {
		roleRepository.removeRoleById(id);
	}

	@Override
	public Role getRoleById(int id) {
		return roleRepository.getRoleById(id);
	}

	@Override
	public void addNewRole(Role role) {
		roleRepository.addNewRole(role);
	}

	@Override
	public void updateRoleById(Role role, int id) {
		roleRepository.updateRoleById(role, id);
	}

}
