package com.app.shoutbox.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.shoutbox.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	private Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	@Autowired
	private UserRepositroy repo;

	public UserDaoImpl() {
		logger.info("Inside constructor of "+this.getClass().getName());
	}
	
	@Override
	public User registerUser(User u) {
		return repo.save(u);
	}

	@Override
	public List<User> getUsers() {
		return repo.findAll();
	}

	@Override
	public User getCurrentUserById(Integer currentUserId) {
		return repo.findOne(currentUserId);
	}

	
	public User loginUser(User u){
		User u1 = repo.findUserByEmailandPassword(u.getEmail(),u.getPassword());
		return u1;
	}
}
