package com.app.shoutbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity()
@Table(name = "user_details")
public class User {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String email;
	private String password;
	private Character gender;
	private Role role;

	private Boolean isUserActive; //Admin approval flag;
	
	//Shout Mapping
	private List<Shouts> shouts = new ArrayList<>(); 
	
	//Comment Mapping
	private List<Comments> comments = new ArrayList<Comments>();
	
	//Friend mapping
	//private List<User> friends = new ArrayList<>();
	
	public User() {
	}
	
	public User(String firstName, String lastName, Date dateOfBirth, String email, String password, Character gender,
			Role role,Boolean isUserActive) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.isUserActive = isUserActive;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "first_name", length = 50, nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 50, nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "email", length = 100, unique = true, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", length = 256, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "gender", nullable = false)
	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 20 , nullable = false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Column(name = "is_user_active", nullable = false,columnDefinition = "boolean default false")
	public Boolean isUserActive() {
		return isUserActive;
	}

	public void setUserActive(Boolean isUserActive) {
		this.isUserActive = isUserActive;
	}

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)	
	@JsonIgnore
	public List<Shouts> getShouts() {
		return shouts;
	}

	public void setShouts(List<Shouts> shouts) {
		this.shouts = shouts;
	}

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	//To maintain bidirectional mapping
	//This is helper method : everytime at inserting shout call this method and pass shout object to this method.
	public void addShouts(Shouts sh) {
		shouts.add(sh);
		sh.setOwner(this);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", email=" + email + ", password=" + password + ", gender=" + gender + ", role=" + role
				+ ", isUserActive=" + isUserActive + "]";
	}
	
}
