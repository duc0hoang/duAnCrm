package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.constant.AttributeConstant;
import com.myclass.dbconnection.MySqlConnection;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Job;
import com.myclass.entity.Task;
import com.myclass.entity.User;

@Component
public class TaskRepository implements ITaskRepository{
	List<Task> taskListWithUserId, taskListWithJobId, allTask;
	List<TaskDto> taskDtoList;
	
	public TaskRepository(List<Task> taskListWithUserId,List<Task> taskListWithJobId,List<TaskDto> taskDtoList,List<Task> allTask) {
		this.taskListWithJobId = taskListWithJobId;
		this.taskListWithUserId = taskListWithUserId;
		this.allTask = allTask;
	}

	@Override
	public List<Task> getAllTaskOfUserId(int id) {
		try {
			String query = "SELECT * FROM tasks WHERE user_id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Task task = new Task(result.getInt(AttributeConstant.ID),result.getString(AttributeConstant.NAME),
						result.getString(AttributeConstant.START_DATE),result.getString(AttributeConstant.END_DATE),
						result.getInt(AttributeConstant.USER_ID),result.getInt(AttributeConstant.JOB_ID),result.getInt(AttributeConstant.STATUS_ID));
				
				taskListWithUserId.add(task);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return taskListWithUserId;
	}

	@Override
	public List<Task> getAllTaskOfJobId(int id) {
		try {
			String query = "SELECT * FROM tasks WHERE job_id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Task task = new Task(result.getInt(AttributeConstant.ID),result.getString(AttributeConstant.NAME),
						result.getString(AttributeConstant.START_DATE),result.getString(AttributeConstant.END_DATE),
						result.getInt(AttributeConstant.USER_ID),result.getInt(AttributeConstant.JOB_ID),result.getInt(AttributeConstant.STATUS_ID));
				
				taskListWithJobId.add(task);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return taskListWithJobId;
	}

	@Override
	public List<TaskDto> getAllTaskWithFullName() {
		try {
			String query = "SELECT t.id, t.name, t.start_date, t.end_date, u.fullname, j.name, s.name FROM tasks t left join users u on t.user_id = u.id left join jobs j on t.job_id = j.id left join status s on t.status_id = s.id";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			taskDtoList = new ArrayList<TaskDto>();
			
			while (result.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setId(result.getInt(1));
				taskDto.setName(result.getString(2));
				taskDto.setStartDate(result.getString(3));
				taskDto.setEndDate(result.getString(4));
				taskDto.setUserFullname(result.getString(5));
				taskDto.setJobName(result.getString(6));
				taskDto.setStatusName(result.getString(7));
				
				taskDtoList.add(taskDto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return taskDtoList;
	}

	@Override
	public void removeTaskById(int id) {
		try {
			String query = "DELETE FROM tasks WHERE id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			int result = statement.executeUpdate();
			
			if(result < 1) {
				System.out.println("Delete task with id: " + id + " unseccessfully.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
	}

	@Override
	public Task getTaskById(int id) {
		try {
			String query = "SELECT * FROM tasks WHERE id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				Task task = new Task(result.getInt("id"),result.getString("name"),
						result.getString("start_date"),result.getString("end_date"),
						result.getInt("user_id"),result.getInt("job_id"),result.getInt("status_id"));
				
				return task;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return null;
	}

	@Override
	public void addNewTask(Task task) {
		try {
			String query = "INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id) VALUES (?, ?, ?, ?, ?, ?);";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, task.getName());
			statement.setString(2, task.getStartDate());
			statement.setString(3, task.getEndDate());
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, task.getStatusId());
			
			int result = statement.executeUpdate();
			
			if(result < 1) {
				System.out.println("Add task with name: " + task.getName() + " unseccessfully.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
	}

	@Override
	public void updateTaskById(Task task, int id) {
		try {
			String query = "UPDATE tasks SET name = ?, start_date = ?, end_date = ?, user_id = ?, job_id = ?, status_id = ? WHERE id = ?;";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setString(1, task.getName());
			statement.setString(2, task.getStartDate());
			statement.setString(3, task.getEndDate());
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, task.getStatusId());
			statement.setInt(7, id);
			
			int result = statement.executeUpdate();
			if(result < 1) {
				System.out.println("Update task with id: " + id + " unseccessfully.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
	}

	@Override
	public List<Task> getAllTask() {
		try {
			String query = "SELECT * FROM tasks";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				Task task = new Task(result.getInt("id"),result.getString("name"),
						result.getString("start_date"),result.getString("end_date"),
						result.getInt("user_id"),result.getInt("job_id"),result.getInt("status_id"));
				
				allTask.add(task);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return allTask;
	}

	@Override
	public List<TaskDto> getAllTaskDtoOfUserId(int id) {
		try {
			String query = "SELECT t.id, t.name, t.start_date, t.end_date, u.fullname, j.name, s.name, s.id FROM tasks t left join users u on t.user_id = u.id left join jobs j on t.job_id = j.id left join status s on t.status_id = s.id WHERE u.id = ?";
			Connection connection = MySqlConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			taskDtoList = new ArrayList<TaskDto>();
			
			while (result.next()) {
				TaskDto taskDto = new TaskDto();
				taskDto.setId(result.getInt(1));
				taskDto.setName(result.getString(2));
				taskDto.setStartDate(result.getString(3));
				taskDto.setEndDate(result.getString(4));
				taskDto.setUserFullname(result.getString(5));
				taskDto.setJobName(result.getString(6));
				taskDto.setStatusName(result.getString(7));
				taskDto.setStatusId(result.getInt(8));
				
				taskDtoList.add(taskDto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(AttributeConstant.ERROR + e.getMessage());
		}
		return taskDtoList;
	}

}
