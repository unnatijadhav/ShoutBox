package com.app.shoutbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shoutbox.dao.ShoutDao;
import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

@Service
public class ShoutsServiceImpl implements ShoutsService {

	@Autowired
	private ShoutDao dao;

	@Override
	public List<User> getFriends(User u1) {
		FriendRequestFlag flag = FriendRequestFlag.APPROVED;
		System.out.println("IN SHOUT SERVICE !!!!!!!!!!");
		return dao.getFriends(u1, flag);
	}

	@Override
	public List<Shouts> getFriendsShouts(User u) {
		boolean shoutActive = true;
		return dao.getFriendsShouts(u,shoutActive);
	}

	@Override
	public List<Shouts> getActiveShoutsById(User user) {
		System.out.println("IN SERVEC @@@@" + user);
		boolean shoutActive = true;
		return dao.getActiveShoutsById(user,shoutActive);
	}

}
