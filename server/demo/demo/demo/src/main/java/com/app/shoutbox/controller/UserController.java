package com.app.shoutbox.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shoutbox.model.User;
import com.app.shoutbox.service.UserService;


//@CrossOrigin(origins="http://localhost:4200")

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LogManager.getLogger(UserController.class);
	
	public UserController() {
		this.logger.info("Inside User Controller...");
	}

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User u)
	{
		System.out.println("the user details" + u);
		try {
			return new ResponseEntity<User>(service.registerUser(u), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("User cannot be registered..." + e);
			return new ResponseEntity<String>("User registration failed" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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

}
