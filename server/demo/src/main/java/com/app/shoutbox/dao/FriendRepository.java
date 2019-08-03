package com.app.shoutbox.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Friends;
import com.app.shoutbox.model.User;

public interface FriendRepository extends JpaRepository<Friends, Integer> {

	
	/*	
	 * All PENDING FRIEND user list (status: Pending)
	 * */
	@Query("select u from User u where u.id in ("
			+ "select f.friend from Friends f where f.friend_owner=:currentUser and flag='PENDING') "
			+ "and is_user_active=1 and u.id !=:currentUser")
	List<User> getAllPendingFriendList(@Param("currentUser") User currentUser);

	/*	
	 * GET FRIEND REQUESTS      
	 * */
	@Query("select u from User u where u.id in"
			+ "(select f.friend from Friends f where f.friend_owner=:friend_owner and f.flag=:flag and f.friend!=:friend_owner)")
	List<User> getFriendRequests(@Param("friend_owner") User currentUser,@Param("flag") FriendRequestFlag flag);

	/*	
	 * ACCEPT FRIEND REQUEST      
	 * */
	@Transactional
	@Procedure("PROC_REJECT_APPROVED_FRIENDS")
	void acceptFriendRequest(@Param("flag") String flag,
			@Param("currentUserId") Integer currentUserId,
			@Param("currentFriendId") Integer currentFriendId);

	/*	
	 * insert values with Status PENDING in FRIEND
	 * */
	/*@Modifying
	@Query(value="insert into user_friends(flag,friend_owner,friend) values(?1, ?2, ?3)",nativeQuery=true)
	int postFriendRequest(String flag,User currentUser, User currentFriend);*/

	
	@Transactional
	@Procedure("PROC_FRIEND_REQUEST_ACCEPT_REJECTED")
	void postFriendRequest(@Param("sentFlag") String sentFlag,@Param("pendingFlag") String pendingFlag,
			@Param("currentUserId") Integer currentUserId,
			@Param("currentFriendId") Integer currentFriendId);
	
	
	/*	
	 * REJECT FRIEND REQUEST      
	 * */
	@Transactional
	@Procedure("PROC_REJECT_APPROVED_FRIENDS")
	void rejectFriendRequest(@Param("flag") String flag,
			@Param("currentUserId") Integer currentUserId,
			@Param("currentFriendId") Integer currentFriendId);

	/*	
	 * REJECT APPROVED FRIEND       
	 * */
	@Transactional
	@Procedure("PROC_REJECT_APPROVED_FRIENDS")
	void rejectApprovedFriend(@Param("flag") String flag,
			@Param("currentUserId") Integer currentUserId,
			@Param("currentFriendId") Integer currentFriendId);
	
	
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	/*	
	 * All REJECTED FRIENDS user list (status: Rejected)
	 * */
	@Query("select u from User u where u.id in ("
			+ "select f.friend from Friends f where f.friend_owner=:currentUser and flag='REJECTED') "
			+ "and is_user_active=1 and u.id !=:currentUser")
	List<User> getAllRejectedFriendList(@Param("currentUser") User currentUser);

	
	/*	
	 * All UN-FRIEND user list (status: None, Pending, Rejected)
	 * */
	@Query("select u from User u where u.id not in ("
			+ "select f.friend from Friends f where f.friend_owner=:currentUser and flag in ('SENT','PENDING','APPROVED'))"
			+ "and is_user_active=1 and u.id !=:currentUser and role='USER'")
	List<User> getAllUnfriendList(@Param("currentUser") User currentUser);

	
}
