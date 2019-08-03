package com.app.shoutbox.service;

import java.util.List;

import com.app.shoutbox.model.User;


public interface UserService {
	public User registerUser(User u);
	
	User registerUser(User u);
	String sendFriendReq(int sender,int rec);
	List<User> getUsers();
	User getCurrentUserById(Integer currentUserId);
	User loginUser(User u);
}
