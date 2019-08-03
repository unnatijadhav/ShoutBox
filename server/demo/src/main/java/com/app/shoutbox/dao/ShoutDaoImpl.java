package com.app.shoutbox.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

@Repository
@Transactional
public class ShoutDaoImpl implements ShoutDao {
	private Logger logger = LogManager.getLogger(ShoutDaoImpl.class);

	@Autowired
	private ShoutsRepository repo;

	/*
	 * AUTHOR: SADIYA SHAIKH
	 */
	@Override
	public List<User> getFriends(User u1, FriendRequestFlag flag) {
		logger.info("inside shoutDaoImpl.getFriends()");
		return repo.findByOwner(u1, flag);
	}

	@Override
	public List<Shouts> getFriendsShouts(User u, boolean shoutActive) {
		logger.info("inside shoutDaoImpl.getFriendsShouts()");
		return repo.findByOwnerAndShoutActive(u, shoutActive);
	}

	@Override
	public List<Shouts> getActiveShoutsById(User user, boolean shoutActive) {
		logger.info("inside shoutDaoImpl.getActiveShoutsById()");
		return repo.findByOwnerAndShoutActive(user, shoutActive);
	}

	public Shouts getShoutById(int currentShoutId) {
		logger.info("Current Shout : " + repo.findOne(currentShoutId));
		return repo.findOne(currentShoutId);
	}

	/*
	 * AUTHOR: SADIYA SHAIKH
	 */
	@Override
	public Shouts postShout(Shouts s) {
		logger.info("inside shoutDaoIml.postShout()");
		return repo.save(s);
	}

	/*
	 * AUTHOR: PRASHANT SHARMA
	 */
	@Override
	public int reportCurrentShout(Integer currentShoutId) {
		return repo.reportCurrentShout(currentShoutId);
	}

	@Override
	public int deleteCurrentShout(Integer currentShoutId) {
		return repo.deleteCurrentShout(currentShoutId);
	}
}
