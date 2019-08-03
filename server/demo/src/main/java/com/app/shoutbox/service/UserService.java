package com.app.shoutbox.service;

import java.util.List;

import com.app.shoutbox.model.FriendRequestFlag;
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
	
	/*	 All PENDING FRIEND users (status: none, pending and rejected) */	
	List<User> getAllPendingFriendList(int currentUserId);
	
	
	/*	 ACCEPT FRIEND REQUEST   */
	int acceptFriendRequest(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId);
	
	/*	  SEND FRIEND REQUEST */
	int postFriendRequest(FriendRequestFlag sentFlag,FriendRequestFlag pendingFlag,Integer currentUserId, Integer currentFriendId);

	/*	 REJECT FRIEND REQUEST  */
	int rejectFriendRequest(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId);

	/*	  REJECT APPROVED FRIEND   */
	int rejectApprovedFriend(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId);

	/*  All REJECTED FRIENDS user list (status: Rejected) */
	List<User> getAllRejectedFriendList(int currentUserId);
	
	/* All UN-FRIEND users (status: none, pending and rejected)  */
	List<User> getAllUnfriendList(int currentUserId);
}
