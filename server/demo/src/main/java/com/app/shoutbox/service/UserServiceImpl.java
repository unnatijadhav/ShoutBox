package com.app.shoutbox.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shoutbox.dao.FriendDao;
import com.app.shoutbox.dao.UserDao;
import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao dao;
	@Autowired
	private FriendDao dao1;
	
	@Override
	public User registerUser(User u) {
		return dao.registerUser(u);
	}
	@Override
	public boolean verifyEmail(String mail) {
		
		System.out.println("return from fb: " + dao.verifyMail(mail));
		if(dao.verifyMail(mail) > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Shouts> getCurrentUserShouts(int id) {
		User user  = dao.getUserById(id);
		return dao.getCurrentUserShouts(user);
	}
	
	@Override
	public User loginUser(User u) {
		return dao.loginUser(u);
	}
	
	@Override
	public User getCurrentUserById(Integer currentUserId) {
		return dao.getCurrentUserById(currentUserId);
	}
	
	/*	
	 * All PENDING FRIEND user list (status: Pending)
	 * */	
	@Override
	public List<User> getAllPendingFriendList(int currentUserId) {
		User currentUser = dao.getUserById(currentUserId);
		return dao1.getAllPendingFriendList(currentUser);
	}
	
	
	/*	
	 * ACCEPT FRIEND REQUEST      
	 * */
	@Override
	public int acceptFriendRequest(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId) {
		return dao1.acceptFriendRequest(flag, currentUserId, currentFriendId);
	}
	
	/*	
	 * SEND FRIEND REQUEST      
	 * */

	@Override
	public int postFriendRequest(FriendRequestFlag sentFlag,FriendRequestFlag pendingFlag,Integer currentUserId, Integer currentFriendId) {
		return dao1.postFriendRequest(sentFlag,pendingFlag,currentUserId,currentFriendId);
	}
	
	/*	
	 * REJECT FRIEND REQUEST      
	 * */
	@Override
	public int rejectFriendRequest(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId) {
		return dao1.rejectFriendRequest(flag, currentUserId, currentFriendId);
	}
	
	/*	
	 * REJECT APPROVED FRIEND
	 * */
	@Override
	public int rejectApprovedFriend(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId) {
		return dao1.rejectApprovedFriend(flag, currentUserId, currentFriendId);
	}
	
	/*	
	 * All REJECTED FRIENDS user list (status: Rejected)
	 * */
	@Override
	public List<User> getAllRejectedFriendList(int currentUserId) {
		User currentUser = dao.getUserById(currentUserId);
		return dao1.getAllRejectedFriendList(currentUser);
	}
	
	/*	
	 * All UN-FRIEND users (status: none, pending and rejected)      
	 * */
	@Override
	public List<User> getAllUnfriendList(int currentUserId) {
		User currentUser = dao.getUserById(currentUserId);
		return dao1.getAllUnfriendList(currentUser);
	}
}
