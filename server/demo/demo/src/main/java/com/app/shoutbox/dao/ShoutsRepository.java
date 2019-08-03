package com.app.shoutbox.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

public interface ShoutsRepository extends JpaRepository<Shouts, Integer>{

	@Query("Select f.friend from Friends f where f.friend_owner like ?1 and f.flag like ?2")
	public List<User> findByOwner(User u1,FriendRequestFlag flag);

	//public List<Shouts> findByOwnerAndShoutActive(User u,boolean shoutActive);
	@Query("Select s from Shouts s where s.owner like ?1 and s.shoutActive like ?2")
	public List<Shouts> findByOwnerAndShoutActive(User user,boolean shoutActive);
}
