package com.app.shoutbox.service;

import java.util.List;

import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

public interface ShoutsService {
	List<User> getFriends(User u1);
	List<Shouts> getFriendsShouts(User u);
	List<Shouts> getActiveShoutsById(User user);
}
