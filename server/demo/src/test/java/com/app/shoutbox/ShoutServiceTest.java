//package com.app.shoutbox;
//
//import java.util.Date;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import com.app.shoutbox.dao.ShoutsRepository;
//import com.app.shoutbox.model.Role;
//import com.app.shoutbox.model.Shouts;
//import com.app.shoutbox.model.User;
//import com.app.shoutbox.service.ShoutsServiceImpl;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ShoutServiceTest {
//
//	private static Logger logger = LogManager.getLogger(ShoutServiceTest.class);
//
//	@Mock
//	private ShoutsRepository repo;
//	@InjectMocks
//	private static ShoutsServiceImpl shoutService;
//
//	private Shouts shout;
//	private User user;
//
//	public ShoutServiceTest() {
//		shoutService = new ShoutsServiceImpl();
//	}
//
//	@BeforeClass
//	public static void beforeMethod() {
//		shoutService = new ShoutsServiceImpl();
//		logger.info("\"Operations :: \"+shoutService");
//	}
//
//	@Test
//	public void testFindShout() {
//		user = new User();
//		user.setId(8);
//		user.setEmail("sadiya@gmail.com");
//		user.setFirstName("Sadiya");
//		user.setLastName("Shaikh");
//		user.setRole(Role.USER);
//		user.setGender('F');
//		user.setUserActive(true);
//		shout = new Shouts();
//		Integer shoutId = 2;
//		shout.setShoutActive(true);
//		shout.setShoutContentType("text");
//		shout.setTimestamp(new Date("2019-06-26 19:27:48"));
//		Mockito.when(repo.findOne(2)).thenReturn(shout);
//		Shouts shouts = (Shouts) shoutService.getActiveShoutsById(user);
//		Assert.assertNotNull(shouts);
//		Assert.assertEquals(shout, shouts);
//	}
//
//	@AfterClass
//	public static void afterMethod() {
//		shoutService = null;
//		logger.info("after null :: " + shoutService);
//	}
//
//}
