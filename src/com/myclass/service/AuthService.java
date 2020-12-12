package com.myclass.service;

import org.springframework.stereotype.Component;

import com.myclass.entity.User;
import com.myclass.repository.IAuthRepository;
@Component
public class AuthService implements IAuthService{
	IAuthRepository authRepository;
	
	public AuthService(IAuthRepository authRepository) {
		this.authRepository = authRepository;
	}
	
	@Override
	public User login(String email) {
		return authRepository.login(email);
	}

}
