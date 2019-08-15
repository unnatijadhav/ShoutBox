package com.app.shoutbox.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

public interface ShoutsRepository extends JpaRepository<Shouts, Integer>{

	@Query(" select u from User u where u.id in (select f.friend from Friends f where f.friend_owner like ?1 and f.flag like ?2) and u.userActive=1")
	public List<User> findByOwner(User u1,FriendRequestFlag flag);

	@Query("Select s from Shouts s where s.owner like ?1 and s.shoutActive like ?2")
	public List<Shouts> findByOwnerAndShoutActive(User user,boolean shoutActive);

	@Modifying
	@Query("update Shouts s set is_reported = 1 where s.id = ?1")
	public int reportCurrentShout(Integer currentShoutId);
	
	@Modifying
	@Query("update Shouts s set is_shout_active = 0 where s.id = ?1")
	public int deleteCurrentShout(Integer currentShoutId);
	
	
}
