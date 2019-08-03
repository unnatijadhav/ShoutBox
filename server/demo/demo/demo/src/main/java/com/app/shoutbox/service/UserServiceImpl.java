package com.app.shoutbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shoutbox.dao.UserDao;
import com.app.shoutbox.model.User;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserDao dao;
	
	@Override
	public User registerUser(User u) {
		return dao.registerUser(u);
	}

}
