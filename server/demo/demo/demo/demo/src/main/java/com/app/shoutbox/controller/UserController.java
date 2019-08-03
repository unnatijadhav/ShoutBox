package com.app.shoutbox.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.shoutbox.model.Role;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;
import com.app.shoutbox.service.ShoutsService;
import com.app.shoutbox.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LogManager.getLogger(UserController.class);
	
	public UserController() {
		this.logger.info("inside constructor of "+this.getClass().getName());
	}

	@Autowired
	private UserService service;
	@Autowired
	private ShoutsService shoutService;

	/*
	 * GET USER BY ID... (Unnati Jadhav)
	*/	
	@PostMapping("/current")
	public User getUserById(@RequestBody Integer currentUserId) {
		System.out.println("INSIDE " + currentUserId);
		try {
			return service.getCurrentUserById(currentUserId);
		}catch(Exception ex) {
			return null;
		}
	}

	/*
	 * GET USERS
	*/
	@GetMapping("/getUsers")
	public ResponseEntity<?> getUsers() {
		System.out.println("GetUSERS" + service.getUsers());
		try {
			return new ResponseEntity<List<User>>(service.getUsers(), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("User cannot be registered..." + e);
			return new ResponseEntity<String>("User registration failed" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * REGISTER NEW USERS (PRASHANT SHARMA)
	*/
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User u) {
		System.out.println("the user details" + u);
		try {
			return new ResponseEntity<User>(service.registerUser(u), HttpStatus.CREATED);
		} catch (Exception e) {
			this.logger.info("User cannot be registered..." + e);
			return new ResponseEntity<String>("User registration failed" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

<<<<<<< HEAD:MajorProject/server/demo/demo/demo/src/main/java/com/app/shoutbox/controller/UserController.java
//	@PostMapping("/login")
//	public User loginUser(@RequestBody User u) {
//		System.out.println("The email and password are ::" + u.getEmail() + " " + u.getPassword());
//		try {
//			return dao.getUser(u.getEmail(), u.getPassword());
//		} catch (Exception e) {
//			System.out.println("Cannot Login user..." + e.getMessage());
//		}
//		return null;
//
//	}
=======
	@PostMapping("/friendreq/{sender}/{rec}")
	public ResponseEntity<?> sendFriendReq(@PathVariable("sender") int sender, @PathVariable("rec") int rec) {
		System.out.println("in friend req method");
		try {
			System.out.println("inside  sender bcontroller friendreq" + sender);
			System.out.println("inside rec controller friendreq" + rec);
			return new ResponseEntity<String>(service.sendFriendReq(sender, rec), HttpStatus.CREATED);

		} catch (Exception e) {
			// System.out.println("User cannot be registered..." + e);
			return new ResponseEntity<String>("friend req failer" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 *getting friendlist 
	 */
	@GetMapping("/getFriends/{id}")
	public ResponseEntity<?> getFriends(@PathVariable("id") Integer id) {
		User u1 = service.getCurrentUserById(id);
		System.out.println("IN GETFRIENDS @@@@@@@@@@" + shoutService.getFriends(u1));
		System.out.println("AFTER USER INIT GETFRIENDS @@@@@@@@@@");

		try {		
			return new ResponseEntity<List<User>>(shoutService.getFriends(u1), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Get Friends failed" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
			return new ResponseEntity<String>("friend req failer" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User u) {
		this.logger.info("The email and password are ::" + u.getEmail() + " " + u.getPassword());
		try {
			User loggedInUser = service.loginUser(u);
			if(loggedInUser != null)
				return new ResponseEntity<User>(loggedInUser,HttpStatus.ACCEPTED );
			else 
				throw new Exception();
		} catch (Exception e) {
			return new ResponseEntity<String>("Username or Password is incorrect ! Please try again !!",
					HttpStatus.UNAUTHORIZED);
		}
	}
>>>>>>> b5d6ed644a87d24bd11a85dea82ad37cd0f0f491:MajorProject/server/ShoutBox/src/main/java/com/app/shoutbox/controller/UserController.java
//
//	@SuppressWarnings("deprecation")
//	@PutMapping
//	public ResponseEntity<?> updateUserDetails(@RequestBody User u) {
//		System.out.println("Details to be updated ::" + u);
//		try {
//			return new ResponseEntity<User>(dao.updateUserDetails(u), HttpStatus.CREATED);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.METHOD_FAILURE);
//		}
//	}

	/*
	 * getting friends's shouts CHECK SHOUT IS NULL
	 */	
	@GetMapping("/getFriendsShouts")
	public List<Shouts> getFriendsShouts() {
		User u1 = new User(1, "Unnati", "Jadhav", new Date("09/06/1996"), "unu2hd.ch", "fhjh", 'F', Role.USER, true);
		List<User> myFriends = shoutService.getFriends(u1);
		myFriends.add(u1);
		List<Shouts> allShouts = new ArrayList<Shouts>();

		for (User u : myFriends) {
			if (u != null) {
				if (shoutService.getActiveShoutsById(u) != null) {
					allShouts.addAll(shoutService.getActiveShoutsById(u));
					System.out.println("ALLSHOUTS !!!" + allShouts);
				} else {
					System.out.println("SHOUT IS NULL!!!!!!!!!!!!!!!"); /// redirect to login
				}
			}
		}
		
		try {
			return allShouts;

		} catch (Exception e) {
			return null;
		}
	}
}
