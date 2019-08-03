package com.app.shoutbox.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shoutbox.controller.AdminController;
import com.app.shoutbox.dao.CommentRepository;
import com.app.shoutbox.dao.ShoutDao;
import com.app.shoutbox.dao.ShoutsRepository;
import com.app.shoutbox.dao.UserRepositroy;
import com.app.shoutbox.model.Comments;
import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

@Service
public class ShoutsServiceImpl implements ShoutsService {

	@Autowired
	private ShoutDao dao;
	private Logger logger = LogManager.getLogger(ShoutsServiceImpl.class);
	
	@Autowired
	ShoutsRepository repo;
	@Autowired
	UserRepositroy userRepo;
	@Autowired
	CommentRepository commentRepo;

	@Override
	public List<User> getFriends(User u1) {
		FriendRequestFlag flag = FriendRequestFlag.APPROVED;
		logger.info("IN SHOUT SERVICE !!!!!!!!!!");
		return dao.getFriends(u1, flag);
	}

	@Override
	public List<Shouts> getFriendsShouts(User u) {
		boolean shoutActive = true;
		return dao.getFriendsShouts(u,shoutActive);
	}

	@Override
	public List<Shouts> getActiveShoutsById(User user) {
		logger.info("IN SERVEC @@@@" + user);
		boolean shoutActive = true;
		return dao.getActiveShoutsById(user,shoutActive);
	}

	@Override
	public Shouts postShout(Shouts s) {
		logger.info("ShoutServiceImp dao.postShout");
		return dao.postShout(s);
	}

	@Override
	public int reportCurrentShout(Integer currentShoutId) {
		return dao.reportCurrentShout(currentShoutId);		
	}
	
	@Override
	public int deleteCurrentShout(Integer currentShoutId) {
		return dao.deleteCurrentShout(currentShoutId);		
	}

	@Override
	public Shouts getShoutById(Integer currentShoutId) {
		return dao.getShoutById(currentShoutId);
	}
	
	@Override
	public void postComment(int currentUserId, int currentShoutId, String comments){
		Comments comment = new Comments();
		comment.setUser(userRepo.findOne(currentUserId));
		comment.setShout(repo.findOne(currentShoutId));
		comment.setContent(comments);
		commentRepo.save(comment);
	}

}
