package com.app.shoutbox.dao;

import java.util.List;

import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

public interface UserDao {

	public User registerUser(User u);
	public Integer verifyMail(String mail);
	public List<Shouts> getCurrentUserShouts(User user);
	public User getUserById(int id);
	public User getCurrentUserById(Integer currentUserId); 	
	public User loginUser(User u);
}
