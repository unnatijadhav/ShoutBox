package com.app.shoutbox.service;

import java.util.List;

import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

public interface UserService {
	
	/*
	 * Author: Prashant Sharma
	 */
	public User registerUser(User u);
	public boolean verifyEmail(String mail);
	public List<Shouts> getCurrentUserShouts(int id);

	
	
	/*
	 * Author: Alap Tekadpande
	 */
	public User loginUser(User u);

	
	/*
	 * Author: Unnati Jadhav
	 */
	public User getCurrentUserById(Integer currentUserId);
}
