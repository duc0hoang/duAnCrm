package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

import com.myclass.dbconnection.MySqlConnection;
import com.myclass.entity.User;
@Component
public class AuthRepository implements IAuthRepository{

	@Override
	public User login(String email) {
		User user = null;
		try {
			String query = "SELECT * FROM users WHERE email = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, email);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				user = new User();
				user.setId(result.getInt("id"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setFullname(result.getString("fullname"));
				user.setAvatar(result.getString("avatar"));
				user.setRole_id(result.getInt("role_id"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
		return user;
	}

}
