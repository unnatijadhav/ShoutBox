package com.app.shoutbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shoutbox.dao.UserDao;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	/*
	 * AUTHOR: Prashant Sharma
	*/
	@Override
	public User registerUser(User u) {
		return dao.registerUser(u);
	}
	@Override
	public boolean verifyEmail(String mail) {
		
		System.out.println("return from fb: " + dao.verifyMail(mail));
		if(dao.verifyMail(mail) > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Shouts> getCurrentUserShouts(int id) {
		User user  = dao.getUserById(id);
		return dao.getCurrentUserShouts(user);
	}
	
	
	
	/*
	 * AUTHOR: AlapTekadPande
	*/
	@Override
	public User loginUser(User u) {
		return dao.loginUser(u);
	}
	
	
	/*
	 * AUTHOR: Unnati Jadhav
	*/
	@Override
	public User getCurrentUserById(Integer currentUserId) {
		return dao.getCurrentUserById(currentUserId);
	}
}
