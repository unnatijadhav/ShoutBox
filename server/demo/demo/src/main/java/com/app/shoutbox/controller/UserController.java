package com.app.shoutbox.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;
import com.app.shoutbox.service.ShoutsService;
import com.app.shoutbox.service.UserService;


@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService service;
	@Autowired
	private ShoutsService shoutService;

	/*
	 * User Registration (PRASHANT SHARMA)
	 */
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User u)
	{
		u.setUserActive(false);
		System.out.println("the user details" + u);
		try {
			return new ResponseEntity<User>(service.registerUser(u), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("User cannot be registered..." + e);
			return new ResponseEntity<String>("User registration failed" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * VERIFY EMAIL ID UNIQUE or NOT (PRASHANT SHARMA)
	 */
	@PostMapping("/check-email")
	public HttpStatus verifyEMail(@RequestBody String mail) {
		System.out.println("entered mail: " + mail);
		try {
			if (service.verifyEmail(mail))
				return HttpStatus.FOUND;
			else
				return HttpStatus.NOT_FOUND;
		} catch (Exception e) {
			return HttpStatus.NOT_FOUND;
		}
	}

	/*
	 * GET CURRENT USER -> SHOUTS (PRASHANT SHARMA)
	 */
	
	@GetMapping("/myShouts/{id}")
	public List<Shouts> getCurrentUserShouts(@PathVariable("id") int id){
		return service.getCurrentUserShouts(id);
	}
	
	
	
	
	/*
	 * User LOGIN (ALAP TEKADPANDE)
	 */
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

	
	
	/*
	 * GET USER BY ID... (Unnati Jadhav)
	*/	
	@GetMapping("/current/{currentUserId}")
	public User getUserById(@PathVariable("currentUserId") Integer currentUserId) {
		System.out.println("INSIDE " + currentUserId);
		try {
			return service.getCurrentUserById(currentUserId);
		}catch(Exception ex) {
			return null;
		}
	}
	
	/*
	 * getting friends's shouts CHECK SHOUT IS NULL  (UNNATI JADHAV)
	 */	
	@GetMapping("/get-friends-shouts/{currentUserId}")
	public ResponseEntity<List<Shouts>> getFriendsShouts(@PathVariable("currentUserId") Integer currentUserId) {
		
		System.out.println("<<<<<<< HELP ME" + currentUserId);
		
		try {
			User u1 = service.getCurrentUserById(currentUserId);
			List<User> myFriends = shoutService.getFriends(u1);
			System.out.println("frd list"+myFriends);
			myFriends.add(u1);
			List<Shouts> allShouts = new ArrayList<Shouts>();

			for (User u : myFriends) {
				if (u != null) {
					if (shoutService.getActiveShoutsById(u) != null) {
						allShouts.addAll(shoutService.getActiveShoutsById(u));
						System.out.println("ALLSHOUTS !!!" + allShouts);
					} else {
						System.out.println("SHOUT IS NULL!!!!!!!!!!!!!!!"); 
						/// redirect to login
					}
				}
			}

			return new ResponseEntity<List<Shouts>>(allShouts, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
	
}
