package com.bridgelabz.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.Dao.UserDao;
import com.bridgelabz.model.Login;
import com.bridgelabz.model.User;

public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	public int register(User user) {
		return userDao.register(user);
	}

	public User validateUser(Login login) {
		return userDao.validateUser(login);
	}

	
}
