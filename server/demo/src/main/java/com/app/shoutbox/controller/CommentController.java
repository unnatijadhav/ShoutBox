/*
 * package com.app.shoutbox.controller;
 * 
 * import org.apache.log4j.LogManager; import org.apache.log4j.Logger; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.app.shoutbox.model.Comment; import com.app.shoutbox.model.Shouts;
 * import com.app.shoutbox.model.User; import
 * com.app.shoutbox.service.CommentService;
 * 
 * @CrossOrigin("http://localhost:4200")
 * 
 * @RestController
 * 
 * @RequestMapping("/user") public class CommentController {
 * 
 * private Logger logger = LogManager.getLogger(UserController.class);
 * 
 * @Autowired private CommentService commentService;
 * 
 * @PostMapping("/post-comment/{currentShoutId}") public ResponseEntity<?>
 * postComment(@PathVariable("currentShoutId") Shouts currentShoutId,
 * 
 * @RequestBody Comment comment) { logger.info("Inside postComment()"); try {
 * comment.setShout(currentShoutId); logger.info("Comment content" + comment);
 * return new ResponseEntity<Comment>(commentService.postComment(comment),
 * HttpStatus.OK); } catch (Exception e) {
 * logger.info("Comment can't be posted. Error occured ."); return new
 * ResponseEntity<String>("Comment posting failed" +
 * e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR); } } }
 */