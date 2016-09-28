package com.spring.hbn.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.datatable.app.dao.UserDao;
import com.spring.hbn.test.AbstrBaseTest;

public class TestUserService extends AbstrBaseTest{
	
	@Autowired
	UserDao userDao;
	
	@Test
	public void sayHello() {
		System.out.println("Hello world !");
	}
}
