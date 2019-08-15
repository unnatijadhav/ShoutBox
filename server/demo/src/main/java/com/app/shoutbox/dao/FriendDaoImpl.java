package com.app.shoutbox.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Friends;
import com.app.shoutbox.model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao{

	private Logger logger = LogManager.getLogger(FriendDaoImpl.class);
	@Autowired
	private FriendRepository repo;
	private UserRepositroy helper;

	

	/*	
	 * All PENDING FRIEND user list (status: Pending)
	 * */
	@Override
	public List<User> getAllPendingFriendList(User currentUser) {
		return repo.getAllPendingFriendList(currentUser);
	}
	
	/*	
	 * ACCEPT FRIEND REQUEST      
	 * */
	@Override
	public int acceptFriendRequest(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId) {
		repo.acceptFriendRequest(flag.toString(),currentUserId,currentFriendId);
		return 1;
	}
	
	/*	
	 * SEND FRIEND REQUEST      
	 * */
	@Override
	public int postFriendRequest(FriendRequestFlag sentFlag,FriendRequestFlag pendingFlag,Integer currentUserId, Integer currentFriendId) {
//		System.out.println("inside post friend request:  flag: " + sentFlag);
		repo.postFriendRequest(sentFlag.toString(),pendingFlag.toString(),currentUserId,currentFriendId);
		return 1;
	}

	/*	
	 * REJECT FRIEND REQUEST      
	 * */
	@Override
	public int rejectFriendRequest(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId) {
		repo.rejectFriendRequest(flag.toString(),currentUserId,currentFriendId);
		return 1;
	}
	
	/*	
	 * REJECT APPROVED FRIEND   
	 * */
	@Override
	public int rejectApprovedFriend(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId) {
		repo.rejectApprovedFriend(flag.toString(),currentUserId,currentFriendId);
		return 1;
	}
	
	/*	
	 * All REJECTED FRIENDS user list (status: Rejected)
	 * */
	@Override
	public List<User> getAllRejectedFriendList(User currentUser) {
		return repo.getAllRejectedFriendList(currentUser);
	}

	/*	
	 * All UN-FRIEND users (status: none, pending and rejected)      
	 * */
	@Override
	public List<User> getAllUnfriendList(User currentUser) {
		return repo.getAllUnfriendList(currentUser);
	}
	
}
