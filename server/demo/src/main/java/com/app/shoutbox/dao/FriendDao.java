package com.app.shoutbox.dao;

import java.util.List;

import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.User;

public interface FriendDao {


	public String sendFriendReq(int user_id_sender , int user_id_recevier);
	/*	
	 * All PENDING FRIEND user list (status: Pending)
	 * */
	public List<User> getAllPendingFriendList(User currentUser); 
	//public List<User> getFriendRequests(User currentUser, FriendRequestFlag flag); 

	/*	
	 * ACCEPT FRIEND REQUEST      
	 * */
	public int acceptFriendRequest(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId);
	
	/*	
	 * SEND FRIEND REQUEST      
	 * */
	public int postFriendRequest(FriendRequestFlag sentFlag,FriendRequestFlag pendingFlag,Integer currentUserId, Integer currentFriendId);

	/*	
	 * REJECT FRIEND REQUEST      
	 * */
	public int rejectFriendRequest(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId); 

	/*	
	 * REJECT APPROVED FRIEND
	 * */
	public int rejectApprovedFriend(FriendRequestFlag flag, Integer currentUserId, Integer currentFriendId);

	/*	
	 * All REJECTED FRIENDS user list (status: Rejected)
	 * */
	public List<User> getAllRejectedFriendList(User currentUser);
	
	/*	
	 * All UN-FRIEND users (status: none, pending and rejected)      
	 * */
	public List<User> getAllUnfriendList(User currentUser);
}
