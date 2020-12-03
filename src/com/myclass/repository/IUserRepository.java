package com.myclass.repository;

import java.util.List;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public interface IUserRepository {
	List<UserDto> getAllUserWithRole();

	User getUserById(int id);

	void removeUserById(int id);

	void updateUserById(User user, int id);

	void addNewUser(User user);
}
