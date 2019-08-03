package com.app.shoutbox.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.shoutbox.model.Comments;
import com.app.shoutbox.model.Shouts;

public interface CommentRepository extends JpaRepository<Comments, Integer> {

	/*
	 * @Query("select c from Comments c where c.shout like ?1") public
	 * List<Comments> findCommentsByShout(Shouts currentShoutId);
	 */
	/*
	 * @Query("select c from Comments c where c.shout =: currentShout and c.user in ("
	 * + "select u from User u where u.userActive = 1)") public List<Comments>
	 * getCommentsByShoutAndIsUserActive(Shouts currentShout);
	 */
	@Query("select c from Comments c where c.shout like ?1 and c.user in ("
			+ "select u from User u where u.userActive = 1)")
	public List<Comments> getCommentsByShoutAndIsUserActive(Shouts currentShout);

}
