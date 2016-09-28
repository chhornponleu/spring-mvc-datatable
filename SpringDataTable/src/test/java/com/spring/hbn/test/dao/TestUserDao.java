package com.spring.hbn.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.spring.datatable.app.dao.UserDao;
import com.spring.hbn.test.AbstrBaseTest;

public class TestUserDao extends AbstrBaseTest{
	
	@Autowired
	UserDao userDao;
	
	@Test
	@Transactional
	public void getByUsername() {
		printJson(userDao.getByUsername("ponleu"));
	}
}
