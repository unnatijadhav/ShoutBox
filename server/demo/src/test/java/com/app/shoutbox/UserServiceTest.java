//package com.app.shoutbox;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.junit.*;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import com.app.shoutbox.dao.UserRepositroy;
//import com.app.shoutbox.model.Role;
//import com.app.shoutbox.model.User;
//import com.app.shoutbox.service.UserServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserServiceTest {
//
//	private static Logger logger = LogManager.getLogger(UserServiceTest.class);
//
//	@Mock
//	private UserRepositroy repo;
//	@InjectMocks
//	private static UserServiceImpl userservice;
//
//	private User user;
//
//	public UserServiceTest() {
//		userservice = new UserServiceImpl();
//	}
//
//	@BeforeClass
//	public static void beforeMethod() {
//		userservice = new UserServiceImpl();
//		logger.info("\"Operations :: \"+service");
//	}
//
//	@Test
//	public void testFindUser() {
//		user = new User();
//		Integer userId = 8;
//		user.setEmail("sadiya@gmail.com");
//		user.setFirstName("Sadiya");
//		user.setLastName("Shaikh");
//		user.setRole(Role.USER);
//		user.setGender('F');
//		user.setUserActive(true);
//		Mockito.when(repo.findOne(8)).thenReturn(user);
//		User retrivedUser = userservice.getCurrentUserById(userId);
//		Assert.assertNotNull(retrivedUser);
//		Assert.assertEquals(user, retrivedUser);
//	}
//
//	@AfterClass
//	public static void afterMethod() {
//		userservice = null;
//		logger.info("after null :: " + userservice);
//	}
//
//}
