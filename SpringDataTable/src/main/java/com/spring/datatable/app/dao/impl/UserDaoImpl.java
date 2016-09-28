package com.spring.datatable.app.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.spring.datatable.app.dao.UserDao;
import com.spring.datatable.app.dao.abstr.AbstrBaseDaoImpl;
import com.spring.datatable.app.models.User;

@Repository
public class UserDaoImpl extends AbstrBaseDaoImpl implements UserDao {

	public User getByUsername(String username) {
		Query qur = getSession().createQuery("FROM User WHERE username=:username");
		qur.setParameter("username", username);
		return (User) qur.uniqueResult();
	}

}
