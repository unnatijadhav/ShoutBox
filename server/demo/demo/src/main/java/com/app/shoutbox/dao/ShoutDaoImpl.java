package com.app.shoutbox.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

@Repository
@Transactional
public class ShoutDaoImpl implements ShoutDao{

	@Autowired
	private ShoutsRepository repo;

	@Override
	public List<User> getFriends(User u1, FriendRequestFlag flag) {
		System.out.println("IN SHOUT DAO@@@@@@@@ !!!!!!!!!!");
		return repo.findByOwner(u1, flag);
	}

	@Override
	public List<Shouts> getFriendsShouts(User u,boolean shoutActive) {
		return repo.findByOwnerAndShoutActive(u,shoutActive);
	}

	@Override
	public List<Shouts> getActiveShoutsById(User user,boolean shoutActive) {
		System.out.println("Ins DAO ACTIVE SHOUTS USER$$$$$$" + user);
		return repo.findByOwnerAndShoutActive(user,shoutActive);
	}

}
