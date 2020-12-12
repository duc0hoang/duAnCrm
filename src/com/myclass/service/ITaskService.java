package com.myclass.service;

import java.util.List;

import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;

public interface ITaskService {

	List<Task> getAllTaskOfUserId(int id);

	List<Task> getAllTaskOfJobId(int id);

	List<TaskDto> getAllTaskWithFullName();

	void removeTaskById(int id);

	Task getTaskById(int id);

	void addNewTask(Task task);

	void updateTaskById(Task task, int parseInt);

	int getNumberOfStatus(List<Task> taskListWithUserId, int statusId);

	List<Task> getAllTask();

	List<TaskDto> getAllTaskDtoOfUserId(int id);

	int getNumberOfStatusWithTaskDto(List<TaskDto> taskDtoListWithUserId, int statusId);

}
