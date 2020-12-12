package com.myclass.repository;

import com.myclass.entity.User;

public interface IAuthRepository {

	User login(String email);

}
