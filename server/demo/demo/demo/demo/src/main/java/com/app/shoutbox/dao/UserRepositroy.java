package com.app.shoutbox.dao;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;


public interface UserRepositroy extends JpaRepository<User, Integer> {

    @Query("select u from User u where is_user_active=:p")
	public List<User> getActiveUsers(@Param("p") Boolean flag);
    
    @Query("select u from User u where is_user_active=:p")
	public List<User> getInactiveUsers(@Param("p") Boolean flag);
 
    @Modifying
	@Query(value="update user_details,user_shouts set user_details.is_user_active=:flag1,user_shouts.is_shout_active=:flag2 where user_details.user_id=:uId1 and user_shouts.user_id=:uId2",nativeQuery=true)
	public Integer deleteUser(@Param("flag1") Boolean isUserActive1,@Param("flag2") Boolean isUserActive2,@Param("uId1")Integer id1,@Param("uId2")Integer id2);
    
    @Modifying
    @Query(value="update user_shouts set is_shout_active=:flag where shout_id=:id",nativeQuery=true)
    public Integer deleteShouts(@Param("flag") Boolean isShoutActive,@Param("id") Integer sId);
    
    @Modifying
    @Query(value="update user_details set is_user_active=:flag where user_id=:id",nativeQuery=true)
    public Integer approveUser(@Param("flag") Boolean isUserActive,@Param("id") Integer uId);
    
}
