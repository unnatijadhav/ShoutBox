package com.app.shoutbox.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.shoutbox.model.Role;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;


public interface UserRepositroy extends JpaRepository<User, Integer> {

	/*
	 * AUTHORS: 
	 * 			ABHIJIT ROKADE,
	 * 			PRASHANT SHARMA
	 */
	
					/*	GET ACTIVE USERS */
    @Query("select u from User u where is_user_active=:p and role=:r")
	public List<User> getActiveUsers(@Param("p") Boolean flag,@Param("r") Role role);
    
    				/*  GET INACTIVE USERS	*/
    @Query("select u from User u where is_user_active=:p and role=:r")
	public List<User> getInactiveUsers(@Param("p") Boolean flag,@Param("r") Role role);
    
    				/* 	GET REPORTED SHOUTS	*/
    @Query("select s from Shouts s where is_reported=:p")
	public List<Shouts> getReportedShouts(@Param("p") Boolean flag);
    
    				/*  DELETE USER BY USER ID   */
    @Modifying
	@Query(value="update user_details,user_shouts set user_details.is_user_active=:flag1,user_shouts.is_shout_active=:flag2 where user_details.user_id=:uId1 and user_shouts.user_id=:uId2",nativeQuery=true)
	public Integer deleteUser(@Param("flag1") Boolean isUserActive1,@Param("flag2") Boolean isUserActive2,@Param("uId1")Integer id1,@Param("uId2")Integer id2);
    
    				/*  DELETE SHOUT BY SHOUT ID   */
    @Modifying
    @Query(value="update user_shouts set is_shout_active=:flag where shout_id=:id",nativeQuery=true)
    public Integer deleteShouts(@Param("flag") Boolean isShoutActive,@Param("id") Integer sId);
    
					/*  APPROVE NEWLY REGISTERED USERS  */
    @Modifying
    @Query(value="update user_details set is_user_active=:flag where user_id=:id",nativeQuery=true)
    public Integer approveUser(@Param("flag") Boolean isUserActive,@Param("id") Integer uId);
    
    
    /* *************************************************************************** 
     * 				END FOR AUTHOR ABHIJIT ROKADE 
     * ************************************************************************* */
    
    				/*  VERIFY EMAIL UNIQUE OR NOT  */
    @Query("select count(s) from User s where s.email=:mail")
	public Integer verifyMail(@Param("mail") String mail);

    				/*  GET ALL SHOUTS OF CURRENT USER  */
	@Query("select s from Shouts s where s.owner=:owner and is_shout_active=true")
	public List<Shouts> getCurrentUserShouts(@Param("owner") User user);

	
	/* *************************************************************************** 
     * 				END FOR AUTHOR PRASHANT SHARMA 
     * ************************************************************************* */
   
	@Query("select u from User u where u.email=:email and u.password=:password")
	User findUserByEmailandPassword(@Param("email") String email,@Param("password") String password);
}
