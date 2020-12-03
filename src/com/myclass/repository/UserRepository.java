package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.dbconnection.MySqlConnection;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

@Component
public class UserRepository implements IUserRepository{
	List<UserDto> userDtoList;
	
	public UserRepository(List<UserDto> userDtoList) {
		this.userDtoList = userDtoList;
	}
	
	@Override
	public List<UserDto> getAllUserWithRole() {
		try {
			String query = "SELECT u.id, u.email, u.password, u.fullname, u.avatar, r.description  FROM users u left join roles r on u.role_id = r.id;";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				UserDto userDto = new UserDto();
				userDto.setId(result.getInt("id"));
				userDto.setEmail(result.getString("email"));
				userDto.setFullname(result.getString("fullname"));
				userDto.setRoleDescription(result.getString("description"));
				
				userDtoList.add(userDto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
		return userDtoList;
	}

	@Override
	public User getUserById(int id) {
		try {
			String query = "SELECT * FROM users WHERE id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setFullname(result.getString("fullname"));
				user.setAvatar(result.getString("avatar"));
				user.setRole_id(result.getInt("role_id"));
				
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void removeUserById(int id) {
		try {
			String query = "DELETE FROM users WHERE id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			int result = statement.executeUpdate();
			
			if(result < 1) {
				System.out.println("Delete product with id: " + id + " unseccessfully.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void updateUserById(User user, int id) {
		try {
			String query = "UPDATE users SET fullname = ?, role_id = ? WHERE id = ?;";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, user.getFullname());
			statement.setInt(2, user.getRole_id());
			statement.setInt(3, id);
			
			int result = statement.executeUpdate();
			if(result < 1) {
				System.out.println("Update product with id: " + id + " unseccessfully.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
	}

	@Override
	public void addNewUser(User user) {
		try {
			String query = "INSERT INTO users (email, password, fullname, avatar, role_id) VALUES (?, ?, ?, ?, ?);";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRole_id());
			
			int result = statement.executeUpdate();
			
			if(result < 1) {
				System.out.println("Add user with email: " + user.getEmail() + " unseccessfully.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
	}

}
