package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.constant.AttributeConstant;
import com.myclass.dbconnection.MySqlConnection;
import com.myclass.entity.Job;
import com.myclass.entity.Role;

@Component
public class JobRepository implements IJobRepository{
	List<Job> jobList;
	
	public JobRepository(List<Job> jobList) {
		this.jobList = jobList;
	}
	
	@Override
	public List<Job> getAllJob() {
		try {
			String query = "SELECT * FROM jobs";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Job job = new Job();
				job.setId(result.getInt(AttributeConstant.ID));
				job.setName(result.getString(AttributeConstant.NAME));
				job.setStartDate(result.getString(AttributeConstant.START_DATE));
				job.setEndDate(result.getString(AttributeConstant.END_DATE));
				
				jobList.add(job);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return jobList;
	}

	@Override
	public Job getJobById(int id) {
		try {
			String query = "SELECT * FROM jobs WHERE id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				Job job = new Job();
				job.setId(result.getInt(AttributeConstant.ID));
				job.setName(result.getString(AttributeConstant.NAME));
				job.setStartDate(result.getString(AttributeConstant.START_DATE));
				job.setEndDate(result.getString(AttributeConstant.END_DATE));
				
				return job;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return null;
	}

	@Override
	public void removeJobById(int id) {
		try {
			String query = "DELETE FROM jobs WHERE id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			int result = statement.executeUpdate();
			
			if(result < 1) {
				System.out.println("Delete job with id: " + id + " unseccessfully.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
	}

	@Override
	public void addNewJob(Job job) {
		try {
			String query = "INSERT INTO jobs (name, start_date, end_date) VALUES (?, ?, ?);";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, job.getName());
			statement.setString(2, job.getStartDate());
			statement.setString(3, job.getEndDate());
			
			int result = statement.executeUpdate();
			
			if(result < 1) {
				System.out.println("Add job with name: " + job.getName() + " unseccessfully.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
	}

	@Override
	public void updateJobById(Job job, int id) {
		try {
			String query = "UPDATE jobs SET name = ?, start_date = ?, end_date = ? WHERE id = ?;";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, job.getName());
			statement.setString(2, job.getStartDate());
			statement.setString(3, job.getEndDate());
			statement.setInt(4, id);
			
			int result = statement.executeUpdate();
			if(result < 1) {
				System.out.println("Update job with id: " + id + " unseccessfully.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
	}

}
