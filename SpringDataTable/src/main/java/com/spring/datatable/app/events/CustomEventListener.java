package com.spring.datatable.app.events;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.datatable.app.dao.UserDao;
import com.spring.datatable.app.models.User;

@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {
	@Autowired
	UserDao userDao;
	
	@Override
	@Transactional(readOnly = false)
	public void onApplicationEvent(CustomEvent event) {
		System.out.println(event.toString());
		for (int i = 0; i < 3000; i++) {
			User user = new User();
			user.setUsername(UUID.randomUUID().toString().substring(0,10));
			user.setPassword(Math.random() * 100000 + "");
			userDao.saveOrUpdate(user);
		}
		
		
	}
}
