package com.app.shoutbox.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shoutbox.controller.UserController;
import com.app.shoutbox.dao.FriendDao;
import com.app.shoutbox.dao.UserDao;
import com.app.shoutbox.model.User;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private FriendDao dao1;
	
	public UserServiceImpl() {
		this.logger.info("Inside ctor of "+this.getClass().getName());
	}
	
	@Override
	public User registerUser(User u) {
		return dao.registerUser(u);
	}

	public String sendFriendReq(int sender,int rec) {
		this.logger.info("IN USER SERVICE @@@@@@@");
		return dao1.sendFriendReq(sender, rec);
	}
	@Override
	public List<User> getUsers() {
		return dao.getUsers();
	}

	@Override
	public User getCurrentUserById(Integer currentUserId) {
		return dao.getCurrentUserById(currentUserId);
	}



	@Override
	public User loginUser(User u){
		return dao.loginUser(u);
	}

	
}
