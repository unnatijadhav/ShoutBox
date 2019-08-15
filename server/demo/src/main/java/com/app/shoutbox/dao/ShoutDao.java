package com.app.shoutbox.dao;

import java.util.List;

import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

public interface ShoutDao {
	
	public List<User> getFriends(User u1,FriendRequestFlag flag);
	public List<Shouts> getFriendsShouts(User u,boolean shoutActive);
	public List<Shouts> getActiveShoutsById(User user,boolean shoutActive);
	public Shouts getShoutById(int currentShoutId);
	public Shouts postShout(Shouts s);
	public int reportCurrentShout(Integer currentShoutId);
	public int deleteCurrentShout(Integer currentShoutId);
	
}
