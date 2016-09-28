package com.spring.datatable.app.services.impl;

import org.springframework.stereotype.Service;

import com.spring.datatable.app.models.User;
import com.spring.datatable.app.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	public User getByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		user.setPassword("123");
		return user;
	}

	/***
	 * Authenticate user with Oauth 2 service
	 * @return String as token
	 */
	public String authenticate(String username, String password) {
		//TODO
		return "";
	}
	
	
}
