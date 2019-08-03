package com.app.shoutbox.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.shoutbox.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@Autowired
	private UserRepositroy repo;
	
	@Override
	public User registerUser(User u) {
		return repo.save(u);
	}

	
}
