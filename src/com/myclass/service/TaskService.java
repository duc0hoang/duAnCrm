package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;
import com.myclass.repository.ITaskRepository;

@Component
public class TaskService implements ITaskService{
	ITaskRepository taskRepository;
	
	public TaskService(ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> getAllTaskOfUserId(int id) {
		return taskRepository.getAllTaskOfUserId(id);
	}

	@Override
	public List<Task> getAllTaskOfJobId(int id) {
		return taskRepository.getAllTaskOfJobId(id);
	}

	@Override
	public List<TaskDto> getAllTaskWithFullName() {
		return taskRepository.getAllTaskWithFullName();
	}

	@Override
	public void removeTaskById(int id) {
		taskRepository.removeTaskById(id);
	}

	@Override
	public Task getTaskById(int id) {
		return taskRepository.getTaskById(id);
	}

	@Override
	public void addNewTask(Task task) {
		taskRepository.addNewTask(task);
	}

	@Override
	public void updateTaskById(Task task, int id) {
		taskRepository.updateTaskById(task,id);
	}

}
