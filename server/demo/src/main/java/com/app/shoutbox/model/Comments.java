package com.app.shoutbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments {

	private Integer commentId;
	private String content;
	private Shouts shout;
	private User user;
	
	public Comments() {
		
	}
	public Comments(String content, Shouts shout, User user) {
		super();
		this.content = content;
		this.shout = shout;
		this.user=user;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="comment_id")
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@ManyToOne
	@JoinColumn(name = "shout_id")
	public Shouts getShout() {
		return shout;
	}
	public void setShout(Shouts shout) {
		this.shout = shout;
	}
	
	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", content=" + content + ", shout=" + shout + "]";
	}
	
	
}
