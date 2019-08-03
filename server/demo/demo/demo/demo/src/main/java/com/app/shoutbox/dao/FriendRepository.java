package com.app.shoutbox.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.shoutbox.model.Friends;

public interface FriendRepository extends JpaRepository<Friends, Integer> {

	//@Query("select f from Friends where f.friend=:u and f.friend_owner=:w");
	//public Friends getFriendRef();
}
