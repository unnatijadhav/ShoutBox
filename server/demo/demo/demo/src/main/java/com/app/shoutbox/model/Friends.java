package com.app.shoutbox.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_friends")
public class Friends {

	@EmbeddedId
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = true)
	private FriendRequestFlag flag;

	public Friends() {
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "friend_owner", nullable = false)
	private User friend_owner;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "friend", nullable = false)
	private User friend;

	public Friends(FriendRequestFlag flag, User friend_owner, User friend) {
		super();
		this.flag = flag;
		this.friend_owner = friend_owner;
		this.friend = friend;
	}

	public FriendRequestFlag getFlag() {
		return flag;
	}

	public void setFlag(FriendRequestFlag flag) {
		this.flag = flag;
	}

	public User getFriend_owner() {
		return friend_owner;
	}

	public void setFriend_owner(User friend_owner) {
		this.friend_owner = friend_owner;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	@Override
	public String toString() {
		return "Friends [id=" + id + ", flag=" + flag + ", friend_owner=" + friend_owner + ", friend=" + friend + "]";
	}

	
	
}
