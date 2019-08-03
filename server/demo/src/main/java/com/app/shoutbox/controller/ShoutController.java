package com.app.shoutbox.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.shoutbox.beans.ShoutBean;
import com.app.shoutbox.model.Comments;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;
import com.app.shoutbox.service.CommentService;
import com.app.shoutbox.service.ShoutsService;
import com.app.shoutbox.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shout")
public class ShoutController {

	@Autowired
	ShoutsService service;
	@Autowired
	UserService uService;
	@Autowired
	private CommentService commentService;

	@Autowired
	private ShoutsService shoutService;

	private Logger logger = LogManager.getLogger(ShoutController.class);

	/*
	 * POST COMMENTS
	 */
	@PostMapping("/postmycomment/{currentUserId}/{currentShoutId}")
	public ResponseEntity<String> postMyComment(@PathVariable("currentUserId") Integer currentUserId,
			@PathVariable("currentShoutId") Integer currentShoutId, @RequestParam String comments) {
		logger.info("Inside postComment()");
		try {
			shoutService.postComment(currentUserId, currentShoutId, comments);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Comment can't be posted. Error occured .");
			return new ResponseEntity<String>("Post Comment Failed" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * GET SHOUT BY ID
	 */
	@GetMapping("/getshoutbyid/{currentShoutId}")
	public ResponseEntity<?> getShoutById(@PathVariable Integer currentShoutId) {
		logger.info("Inside getCommentById()");
		try {
			Shouts currentShout = shoutService.getShoutById(currentShoutId);
			logger.info("Shout CommentList ::" + commentService.getCommentsByShout(currentShout));
			currentShout.setComments(commentService.getCommentsByShout(currentShout));
			return new ResponseEntity<Shouts>(currentShout, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Shout Fetching failed ::" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * GET COMMENTS OF A SHOUT
	 */
	@GetMapping("/getcomments/{currentShoutId}")
	public ResponseEntity<?> getCommentsByShoutId(@PathVariable Integer currentShoutId) {
		logger.info("Inside getCommentById()");
		try {
			Shouts currentShout = shoutService.getShoutById(currentShoutId);
			currentShout.setComments(commentService.getCommentsByShout(currentShout));
			logger.info("COMMENTS BY SHOUT ID ::" + commentService.getCommentsByShout(currentShout));
			return new ResponseEntity<List<Comments>>(commentService.getCommentsByShout(currentShout), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Cannot get comments" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * AUTHOR: SADIYA DESCRIPTION: UPLOADING AUDIO,VIDEO AND IMAGE
	 */
	@PostMapping("/upload")
	public ResponseEntity<Shouts> PostShout(@RequestParam("data") MultipartFile imageUpload,
			@RequestParam("timestamp") Date timestamp, @RequestParam("isShoutActive") Boolean isShoutActive,
			@RequestParam("shoutContentType") String shoutContentType, @RequestParam("ownerId") String ownerId)
			throws IOException {

		logger.info("inside PostShouts ::");
		Shouts shout = new Shouts();
		User user = uService.getCurrentUserById(Integer.parseInt(ownerId));

		shout.setShoutActive(isShoutActive);
		// checking content type
		String contentType = imageUpload.getContentType();
		shout.setShoutContentType(contentType);
		shout.setTimestamp(timestamp);
		shout.setOwner(user);
		shout.setData(imageUpload.getBytes());

		try {
			logger.info("file name" + imageUpload.getOriginalFilename());
			// calling service layer method
			return new ResponseEntity<Shouts>(service.postShout(shout), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("err in reg " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * AUTHOR: SADIYA DESCRIPTION: UPLAODING ONLY TEXT
	 */
	@PostMapping("/upload/text")
	public ResponseEntity<Shouts> PostShouText(@RequestParam("data") String textUpload,
			@RequestParam("timestamp") Date timestamp, @RequestParam("isShoutActive") Boolean isShoutActive,
			@RequestParam("shoutContentType") String shoutContentType, @RequestParam("ownerId") String ownerId)
			throws IOException {

		System.out.println("inserting Post shouts ::");

		User user = uService.getCurrentUserById(Integer.parseInt(ownerId));
		Shouts shout = new Shouts();

		shout.setShoutActive(isShoutActive);
		shout.setShoutContentType(shoutContentType);
		shout.setTimestamp(timestamp);
		shout.setOwner(user);
		System.out.println("owner" + user);
		// setting string to bytes
		shout.setData(textUpload.getBytes());

		try {
			System.out.println("text-->" + textUpload);
			// calling service layer method
			return new ResponseEntity<Shouts>(service.postShout(shout), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("err in reg " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * getting friends's shouts CHECK SHOUT IS NULL (UNNATI JADHAV)
	 */
	@GetMapping("/get-friends-shouts/{currentUserId}")
	public ResponseEntity<?> getFriendsShouts(@PathVariable("currentUserId") Integer currentUserId) {

		logger.info("\n\n<<<<<<< HELP ME" + currentUserId);

		Map<String, Object> shoutMap = new HashMap<>();
		List<Shouts> allShouts = new ArrayList<Shouts>();
		List<ShoutBean> allShoutsBean = new ArrayList<ShoutBean>();
		try {
			User user = uService.getCurrentUserById(currentUserId);
			List<User> myFriends = service.getFriends(user);
			myFriends.add(user);

			for (User u : myFriends) {
				if (u != null && service.getActiveShoutsById(u) != null) {
					List<Shouts> myShouts = service.getActiveShoutsById(u);
					for (Shouts shouts : myShouts) {
						if (shouts.getShoutContentType().equals("text")) {
							shouts.setComments(commentService.getCommentsByShout(shouts));
							logger.info("Shouts Comments ::" + shouts.getComments());
							ShoutBean shoutBean = new ShoutBean();
							shoutBean.setId(shouts.getId());
							shoutBean.setTimestamp(shouts.getTimestamp());
							shoutBean.setShoutContentType(shouts.getShoutContentType());
							shoutBean.setShoutActive(shouts.isShoutActive());
							shoutBean.setData(new String(shouts.getData()));
							shoutBean.setOwner(shouts.getOwner());
							shoutBean.setComments(shouts.getComments());
							;
							allShoutsBean.add(shoutBean);
							shoutMap.put("textShouts", allShoutsBean);

						} else {
							shouts.setComments(commentService.getCommentsByShout(shouts));
							logger.info("Shouts Comments ::" + shouts.getComments());
							Shouts tempShout = new Shouts();
							tempShout.setId(shouts.getId());
							tempShout.setTimestamp(shouts.getTimestamp());
							tempShout.setShoutContentType(shouts.getShoutContentType());
							tempShout.setShoutActive(shouts.isShoutActive());
							tempShout.setData(shouts.getData());
							tempShout.setOwner(shouts.getOwner());
							tempShout.setComments(shouts.getComments());
							allShouts.add(tempShout);
							shoutMap.put("blobShouts", allShouts);
						}
					} // end of for-each loop
				}
			}
			return new ResponseEntity<Map<String, Object>>(shoutMap, HttpStatus.ACCEPTED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to fetch Shouts details...." + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	} // end of block

	/*
	 * AUTHOR: PRASHANT SHARMA
	 */
	@GetMapping("/report/{currentShoutId}")
	public HttpStatus reportCurrentShout(@PathVariable("currentShoutId") Integer currentShoutId) {
		try {
			int rows = service.reportCurrentShout(currentShoutId);
			if (rows > 0)
				return HttpStatus.OK;
			else
				return HttpStatus.BAD_REQUEST;
		} catch (Exception ex) {
			return HttpStatus.BAD_REQUEST;
		}
	}

	@GetMapping("/delete-shout/{currentShoutId}")
	public HttpStatus deleteCurrentShout(@PathVariable("currentShoutId") Integer currentShoutId) {
		try {
			int rows = service.deleteCurrentShout(currentShoutId);
			if (rows > 0)
				return HttpStatus.OK;
			else
				return HttpStatus.BAD_REQUEST;
		} catch (Exception ex) {
			return HttpStatus.BAD_REQUEST;
		}
	}
}
