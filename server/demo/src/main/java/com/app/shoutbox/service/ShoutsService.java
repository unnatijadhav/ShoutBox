package com.app.shoutbox.service;

import java.util.List;

import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

public interface ShoutsService {
	
	List<User> getFriends(User u1);
	List<Shouts> getFriendsShouts(User u);
	List<Shouts> getActiveShoutsById(User user);
	Shouts getShoutById(Integer currentShoutId);
	Shouts postShout(Shouts s);
	int reportCurrentShout(Integer currentShoutId);
	int deleteCurrentShout(Integer currentShoutId);
	public void postComment(int currentUserId, int currentShoutId, String comments);
	
}
