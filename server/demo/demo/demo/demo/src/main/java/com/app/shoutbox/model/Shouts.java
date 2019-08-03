package com.app.shoutbox.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "user_shouts")
public class Shouts {

	private Integer id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd' 'HH:mm:ss", timezone="GMT+5:30")
	private Date timestamp;
	private boolean isShoutActive;
	private String shoutContentType;
	private Byte[] data;
	// User Mapping;
	private User owner;

	public Shouts() {

	}

	public Shouts(Date timestamp, boolean shoutActive, String shoutContentType, User owner) {
		super();
		this.timestamp = timestamp;
		this.isShoutActive = shoutActive;
		this.shoutContentType = shoutContentType;
		this.owner = owner;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shout_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shout_timestamp", nullable = false)
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/*
	 * @Column(name = "isShoutActive", nullable = false,columnDefinition =
	 * "boolean default true") public boolean isShoutActive() { return
	 * isShoutActive; }
	 * 
	 * public void setShoutActive(boolean isShoutActive) { this.isShoutActive =
	 * isShoutActive; }
	 */
//	@Column(name = "isShoutActive", nullable = false, columnDefinition = "boolean default true")
//	public boolean isShoutActive() {
//		return isShoutActive;
//	}
//
//	public void setShoutActive(boolean shoutActive) {
//		this.isShoutActive = shoutActive;
//	}
	
	

	@Column(name = "shoutContentType", nullable = false)
	public String getShoutContentType() {
		return shoutContentType;
	}

	@Column(name = "isShoutActive", nullable = false, columnDefinition = "boolean default true")
	public boolean isShoutActive() {
		return isShoutActive;
	}

	public void setShoutActive(boolean isShoutActive) {
		this.isShoutActive = isShoutActive;
	}

	public void setShoutContentType(String shoutContentType) {
		this.shoutContentType = shoutContentType;
	}

	@Column(columnDefinition = "longblob", nullable = false)
	public Byte[] getData() {
		return data;
	}

	public void setData(Byte[] data) {
		this.data = data;
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Shouts [id=" + id + ", timestamp=" + timestamp + ", shoutActive=" + isShoutActive + ", shoutContentType="
				+ shoutContentType + ", owner=" + owner + "]";
	}

}
