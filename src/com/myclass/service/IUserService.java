package com.myclass.service;

import java.util.List;

import com.myclass.dto.UserDto;
import com.myclass.entity.Task;
import com.myclass.entity.User;

public interface IUserService {
	List<UserDto> getAllUserDto();

	User getUserById(int id);

	void removeUserById(int id);

	void updateUserById(User user, int id);

	void addNewUser(User user);

	List<UserDto> getUserDtoOnTaskList(List<Task> taskListWithJobId);

}
