package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.constant.AttributeConstant;
import com.myclass.dbconnection.MySqlConnection;
import com.myclass.entity.Role;
import com.myclass.entity.Status;

@Component
public class StatusRepository implements IStatusRepository{
	List<Status> statusList;
	
	public StatusRepository(List<Status> statusList) {
		this.statusList = statusList;
	}
	
	@Override
	public List<Status> getAllStatus() {
		try {
			String query = "SELECT * FROM status";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Status status = new Status();
				status.setId(result.getInt("id"));
				status.setName(result.getString("name"));
				
				statusList.add(status);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return statusList;
	}

}
