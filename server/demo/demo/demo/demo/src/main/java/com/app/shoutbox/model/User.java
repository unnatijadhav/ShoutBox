package com.app.shoutbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


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
	@JsonIgnore
	private List<Shouts> shouts = new ArrayList<>(); 
	
	
	//Friend mapping
	//private List<User> friends = new ArrayList<>();
	
	//Friends mapping
	@JsonIgnore
	private List<Friends> owner = new ArrayList<>();
	
	@JsonIgnore
	private List<Friends> friend = new ArrayList<>();
		
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

	
	
	public User(Integer id, String firstName, String lastName, Date dateOfBirth, String email, String password,
			Character gender, Role role, boolean isUserActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.isUserActive = isUserActive;
	}

	public User(String firstName, String lastName, Date dateOfBirth, String email, String password, Character gender,
			Role role, boolean isUserActive, List<Shouts> shouts, List<Friends> owner) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.role = role;
		this.isUserActive = isUserActive;
		this.shouts = shouts;
		this.owner = owner;
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
	@JsonManagedReference
	public List<Shouts> getShouts() {
		return shouts;
	}

	public void setShouts(List<Shouts> shouts) {
		this.shouts = shouts;
	}

	//To maintain bidirectional mapping
	//This is helper method : everytime at inserting shout call this method and pass shout object to this method.
	public void addShouts(Shouts sh) {
		shouts.add(sh);
		sh.setOwner(this);
	}
//
//	@OneToOne(cascade = CascadeType.ALL,mappedBy = "friend_owner")
//	public Friends getOwner() {
//		return owner;
//	}

	@OneToMany(mappedBy="friend_owner",cascade = CascadeType.ALL)
	public List<Friends> getOwner() {
		return owner;
	}
	
	public void setOwner(List<Friends> owner) {
		this.owner = owner;
	}
	
	@OneToMany(mappedBy="friend",cascade = CascadeType.ALL)
	public List<Friends> getFriend() {
		return friend;
	}

	public void setFriend(List<Friends> friend) {
		this.friend = friend;
	}

	
//	public void removeShouts(Shouts sh) {
//		shouts.remove(sh);
//		sh.setOwner(null);
//	}

	// mappedBy = "friend_owner"
//	@OneToMany(cascade = CascadeType.ALL)
//	public List<User> getFriends() {
//		return friends;
//	}
//
//	public void setFriends(List<User> friends) {
//		this.friends = friends;
//	}
	
	//To maintain bidirectional mapping
//	public void addFriend(User f) {
//		friends.add(f);
//	}
//	
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", email=" + email + ", password=" + password + ", gender=" + gender + ", role=" + role
				+ ", isUserActive=" + isUserActive + ", shouts=" + shouts + ", owner=" + owner + ", friend=" + friend
				+ "]";
	}
}
