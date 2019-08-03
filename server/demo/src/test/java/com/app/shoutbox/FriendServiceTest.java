//package com.app.shoutbox;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import com.app.shoutbox.dao.FriendRepository;
//import com.app.shoutbox.model.FriendRequestFlag;
//import com.app.shoutbox.model.Role;
//import com.app.shoutbox.model.User;
//import com.app.shoutbox.service.UserServiceImpl;
//
//import junit.framework.Assert;
//
//@RunWith(MockitoJUnitRunner.class)
//public class FriendServiceTest {
//
//	private static Logger logger = LogManager.getLogger(FriendServiceTest.class);
//
//	@Mock
//	private FriendRepository repo;
//	@InjectMocks
//	private static UserServiceImpl friendService;
//
//	private User user;
//
//	public FriendServiceTest() {
//		friendService = new UserServiceImpl();
//	}
//
//	@BeforeClass
//	public static void beforeMethod() {
//		friendService = new UserServiceImpl();
//		logger.info("\"Operations :: \"+friendService");
//	}
//
//	@Test
//	public void testFriend() {
//		user = new User();
//		user.setId(8);
//		user.setEmail("sadiya@gmail.com");
//		user.setFirstName("Sadiya");
//		user.setLastName("Shaikh");
//		user.setRole(Role.USER);
//		user.setGender('F');
//		user.setUserActive(true);
//		user = new User();
//		user.setId(10);
//		user.setEmail("unnati@gmail.com");
//		user.setFirstName("Unnati");
//		user.setLastName("Jadhav");
//		user.setRole(Role.USER);
//		user.setGender('F');
//		user.setUserActive(true);
//		Integer condition = friendService.acceptFriendRequest(FriendRequestFlag.APPROVED, 8, 10);
//		Assert.assertEquals(1,1);
//	}
//
//	@AfterClass
//	public static void afterMethod() {
//		friendService = null;
//		logger.info("after null :: " + friendService);
//	}
//
//}
