package com.app.shoutbox.beans;

import java.util.Date;
import java.util.List;

import com.app.shoutbox.model.Comments;
import com.app.shoutbox.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ShoutBean {

	private Integer id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss", timezone = "GMT+5:30")
	private Date timestamp;
	private boolean isShoutActive;
	private String shoutContentType;
	private String data;
	// User Mapping;
	private User owner;

	// shout comment
	private List<Comments> comments;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isShoutActive() {
		return isShoutActive;
	}

	public void setShoutActive(boolean isShoutActive) {
		this.isShoutActive = isShoutActive;
	}

	public String getShoutContentType() {
		return shoutContentType;
	}

	public void setShoutContentType(String shoutContentType) {
		this.shoutContentType = shoutContentType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}	

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "ShoutBean [id=" + id + ", timestamp=" + timestamp + ", isShoutActive=" + isShoutActive
				+ ", shoutContentType=" + shoutContentType + ", data=" + data + ", owner=" + owner + "]";
	}

}
