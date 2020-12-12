package com.myclass.repository;

import java.util.List;

import com.myclass.dto.TaskDto;
import com.myclass.entity.Task;

public interface ITaskRepository {

	List<Task> getAllTaskOfUserId(int id);

	List<Task> getAllTaskOfJobId(int id);

	List<TaskDto> getAllTaskWithFullName();

	void removeTaskById(int id);

	Task getTaskById(int id);

	void addNewTask(Task task);

	void updateTaskById(Task task, int id);

	List<Task> getAllTask();

	List<TaskDto> getAllTaskDtoOfUserId(int id);

}
