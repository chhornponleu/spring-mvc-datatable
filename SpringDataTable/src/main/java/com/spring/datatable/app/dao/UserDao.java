package com.spring.datatable.app.dao;

import com.spring.datatable.app.models.User;

public interface UserDao {
	User getByUsername(String username);
}
