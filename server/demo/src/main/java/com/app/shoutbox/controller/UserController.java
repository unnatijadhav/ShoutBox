package com.app.shoutbox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.app.shoutbox.beans.ShoutBean;
import com.app.shoutbox.dao.ShoutDao;
import com.app.shoutbox.model.Comments;
import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;
import com.app.shoutbox.service.CommentService;
import com.app.shoutbox.service.ShoutsService;
import com.app.shoutbox.service.UserService;
import com.app.shoutbox.utilities.EncryptPasswords;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LogManager.getLogger(UserController.class);

	private FriendRequestFlag pendingFlag = FriendRequestFlag.PENDING;
	private FriendRequestFlag rejectFlag = FriendRequestFlag.REJECTED;
	private FriendRequestFlag approveFlag = FriendRequestFlag.APPROVED;
	private FriendRequestFlag sentFlag = FriendRequestFlag.SENT;

	@Autowired
	private UserService service;
	@Autowired
	private ShoutsService shoutService;

	
	/*
	 * User Registration 
	 */
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User u) { //@RequestBody  annotation maps the HttpRequest body to a transfer or domain object, enabling automatic deserialization of the inbound HttpRequest body onto a Java object.
		u.setUserActive(false);
		logger.info("the user details" + u);
		try {
			u.setPassword(EncryptPasswords.useEncrptAlgo(u.getPassword()));
			return new ResponseEntity<User>(service.registerUser(u), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info("User cannot be registered..." + e);
			return new ResponseEntity<String>("User registration failed" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * VERIFY EMAIL ID UNIQUE or NOT 
	 */
	@PostMapping("/check-email")
	public HttpStatus verifyEMail(@RequestBody String mail) {
		logger.info("entered mail: " + mail);
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
	 * GET CURRENT USER SHOUTS
	 */

	@GetMapping("/myShouts/{id}")
	public Map<String, Object> getCurrentUserShouts(@PathVariable("id") int id) {

		Map<String, Object> shoutMap = new HashMap<>();
		List<Shouts> allShouts = new ArrayList<Shouts>();
		List<ShoutBean> allShoutsBean = new ArrayList<ShoutBean>();

		try {
			List<Shouts> tempList = service.getCurrentUserShouts(id);
			for (Shouts shouts : tempList) {
				if (shouts.getShoutContentType().equals("text")) {

					ShoutBean sb = new ShoutBean();
					sb.setId(shouts.getId());
					sb.setTimestamp(shouts.getTimestamp());
					sb.setShoutContentType(shouts.getShoutContentType());
					sb.setShoutActive(shouts.isShoutActive());
					sb.setData(new String(shouts.getData()));
					sb.setOwner(shouts.getOwner());

					allShoutsBean.add(sb);
					shoutMap.put("textShouts", allShoutsBean);

				} else {
					Shouts tempShout = new Shouts();
					tempShout.setId(shouts.getId());
					tempShout.setTimestamp(shouts.getTimestamp());
					tempShout.setShoutContentType(shouts.getShoutContentType());
					tempShout.setShoutActive(shouts.isShoutActive());
					tempShout.setData(shouts.getData());
					tempShout.setOwner(shouts.getOwner());

					allShouts.add(tempShout);
					shoutMap.put("blobShouts", allShouts);
				}
			} // end of for-each loop

			return shoutMap;

		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * User LOGIN 
	 */
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User u) {
		this.logger.info("The email and password are ::" + u.getEmail() + " " + u.getPassword());
		try {
			u.setPassword(EncryptPasswords.useEncrptAlgo(u.getPassword()));
			User loggedInUser = service.loginUser(u);
			if (loggedInUser != null && loggedInUser.isUserActive() == true)
				return new ResponseEntity<User>(loggedInUser, HttpStatus.ACCEPTED);
			else if (loggedInUser.isUserActive() == false)
				return new ResponseEntity<String>("You are not Authorized ! Wait for Admin Approval",
						HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Username or Password is incorrect ! Please try again !!",
					HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}

	/*
	 * GET USER BY ID...
	 */
	@GetMapping("/current/{currentUserId}")
	public User getUserById(@PathVariable("currentUserId") Integer currentUserId) {
		logger.info("INSIDE " + currentUserId);
		try {
			return service.getCurrentUserById(currentUserId);
		} catch (Exception ex) {
			return null;
		}
	}

	/*
	 * getting friendlist
	 */
	@GetMapping("/getFriends/{id}")
	public ResponseEntity<?> getFriends(@PathVariable("id") Integer id) {
		User u1 = service.getCurrentUserById(id);
		logger.info("IN GETFRIENDS @@@@@@@@@@" + shoutService.getFriends(u1));

		try {
			return new ResponseEntity<List<User>>(shoutService.getFriends(u1), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Get Friends failed" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * All PENDING FRIEND users (status: none, pending and rejected)
	 */
	@GetMapping("/pending-friends/{id}")
	public List<User> getAllPendingFriendList(@PathVariable("id") int currentUserId) {
		try {
			logger.info("pending-friend-list/ !!!!!!!!!!!!!!");
			List<User> pendingFriendList = service.getAllPendingFriendList(currentUserId);
			return pendingFriendList;
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * ACCEPT FRIEND REQUEST
	 */
	@GetMapping("/accept-friend-request/{currentUserId}/{currentFriendId}")
	public HttpStatus acceptFriendRequest(FriendRequestFlag flag, @PathVariable("currentUserId") Integer currentUserId,
			@PathVariable("currentFriendId") Integer currentFriendId) {
		try {
			flag = this.approveFlag;
			int rows = service.acceptFriendRequest(flag, currentUserId, currentFriendId);
			if (rows > 0)
				return HttpStatus.OK;
			else
				return HttpStatus.NOT_IMPLEMENTED;

		} catch (Exception e) {
			return HttpStatus.FORBIDDEN;
		}
	}

	/*
	 * SEND FRIEND REQUEST
	 */
	@GetMapping("/send-friend-request/{currentUserId}/{currentFriendId}")
	public HttpStatus postFriendRequest(@PathVariable("currentUserId") Integer currentUserId,
			@PathVariable("currentFriendId") Integer currentFriendId) {
		try {
			FriendRequestFlag sentFlag = this.pendingFlag;
			FriendRequestFlag pendingFlag = this.sentFlag;
			int rows = service.postFriendRequest(sentFlag, pendingFlag, currentUserId, currentFriendId);
			if (rows > 0)
				return HttpStatus.OK;
			else
				return HttpStatus.NOT_IMPLEMENTED;

		} catch (Exception e) {
			return HttpStatus.FORBIDDEN;
		}
	}

	/*
	 * REJECT FRIEND REQUEST
	 */
	@GetMapping("/reject-friend-request/{currentUserId}/{currentFriendId}")
	public HttpStatus rejectFriendRequest(FriendRequestFlag flag, @PathVariable("currentUserId") Integer currentUserId,
			@PathVariable("currentFriendId") Integer currentFriendId) {
		try {
			flag = this.rejectFlag;
			int rows = service.rejectFriendRequest(flag, currentUserId, currentFriendId);
			if (rows > 0)
				return HttpStatus.OK;
			else
				return HttpStatus.NOT_IMPLEMENTED;

		} catch (Exception e) {
			return HttpStatus.FORBIDDEN;
		}
	}

	/*
	 * REJECT APPROVED FRIEND
	 */
	@GetMapping("/reject-approved-friend/{currentUserId}/{currentFriendId}")
	public HttpStatus rejectApprovedFriend(FriendRequestFlag flag, @PathVariable("currentUserId") Integer currentUserId,
			@PathVariable("currentFriendId") Integer currentFriendId) {
		try {
			flag = this.rejectFlag;
			int rows = service.rejectApprovedFriend(flag, currentUserId, currentFriendId);
			if (rows > 0)
				return HttpStatus.OK;
			else
				return HttpStatus.NOT_IMPLEMENTED;

		} catch (Exception e) {
			return HttpStatus.FORBIDDEN;
		}
	}

	/*
	 * All REJECTED FRIENDS user list (status: Rejected)
	 */
	@GetMapping("/rejected-friend-list/{currentUserId}")
	public ResponseEntity<?> getAllRejectedFriendList(@PathVariable("currentUserId") Integer currentUserId) {
		try {
			List<User> rejectedFriendList = service.getAllRejectedFriendList(currentUserId);
			return new ResponseEntity<List<User>>(rejectedFriendList, HttpStatus.FOUND);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * All UN-FRIEND users (status: all non (friend, pending) users)
	 */
	@GetMapping("/new-users/{id}")
	public List<User> getAllUnfriendList(@PathVariable("id") int currentUserId) {
		try {
			List<User> unfriendList = service.getAllUnfriendList(currentUserId);
			return unfriendList;
		} catch (Exception e) {
			return null;
		}
	}
}
