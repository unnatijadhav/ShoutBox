package com.app.shoutbox.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.app.shoutbox.model.User;

public interface UserDao {

	public User registerUser(User u); 
	public List<User> getUsers();
	public User getCurrentUserById(Integer currentUserId);
	public User registerUser(User u);
	public User loginUser(User u);
}
