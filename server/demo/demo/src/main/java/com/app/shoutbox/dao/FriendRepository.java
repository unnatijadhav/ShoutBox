package com.app.shoutbox.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shoutbox.model.Friends;

public interface FriendRepository extends JpaRepository<Friends, Integer> {

}
