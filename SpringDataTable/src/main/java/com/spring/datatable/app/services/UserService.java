package com.spring.datatable.app.services;

import com.spring.datatable.app.models.User;

public interface UserService {
	User getByUsername(String username);
	String authenticate(String username, String password);
}
