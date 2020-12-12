package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.constant.AttributeConstant;
import com.myclass.dbconnection.MySqlConnection;
import com.myclass.entity.Role;
import com.myclass.entity.User;
@Component
public class RoleRepository implements IRoleRepository{
	List <Role> roleList;
	
	public RoleRepository(List <Role> roleList) {
		this.roleList = roleList;
	}
	
	@Override
	public List<Role> getAllRole() {
		try {
			String query = "SELECT * FROM roles";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Role role = new Role();
				role.setId(result.getInt(AttributeConstant.ID));
				role.setName(result.getString(AttributeConstant.NAME));
				role.setDescription(result.getString(AttributeConstant.DESCRIPTION));
				
				roleList.add(role);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return roleList;
	}

	@Override
	public void removeRoleById(int id) {
		try {
			String query = "DELETE FROM roles WHERE id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			int result = statement.executeUpdate();
			
			if(result < 1) {
				System.out.println("Delete role with id: " + id + " unseccessfully.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
	}

	@Override
	public Role getRoleById(int id) {
		try {
			String query = "SELECT * FROM roles WHERE id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				Role role = new Role();
				role.setId(result.getInt(AttributeConstant.ID));
				role.setName(result.getString(AttributeConstant.NAME));
				role.setDescription(result.getString(AttributeConstant.DESCRIPTION));
				
				return role;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return null;
	}

	@Override
	public void addNewRole(Role role) {
		try {
			String query = "INSERT INTO roles (name, description) VALUES (?, ?);";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			
			
			int result = statement.executeUpdate();
			
			if(result < 1) {
				System.out.println("Add role with name: " + role.getName() + " unseccessfully.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
	}

	@Override
	public void updateRoleById(Role role, int id) {
		try {
			String query = "UPDATE roles SET name = ?, description = ? WHERE id = ?;";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.setInt(3, id);
			
			int result = statement.executeUpdate();
			if(result < 1) {
				System.out.println("Update role with id: " + id + " unseccessfully.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
	}

}
