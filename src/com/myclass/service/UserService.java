package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.IUserRepository;

@Component
public class UserService implements IUserService{
	IUserRepository userRepository;
	
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDto> getAllUserDto() {
		return userRepository.getAllUserWithRole();
	}

	@Override
	public User getUserById(int id) {
		return userRepository.getUserById(id);
	}

	@Override
	public void removeUserById(int id) {
		userRepository.removeUserById(id);
	}

	@Override
	public void updateUserById(User user, int id) {
		userRepository.updateUserById(user,id);
	}

	@Override
	public void addNewUser(User user) {
		userRepository.addNewUser(user);
	}
	
}
