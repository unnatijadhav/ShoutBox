package com.app.shoutbox.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.shoutbox.controller.AdminController;
import com.app.shoutbox.model.Role;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;
import com.app.shoutbox.utilities.HibernateInitializer;

@Repository
public class AdminDaoImpl implements AdminDao {
	private Logger logger = LogManager.getLogger(AdminDaoImpl.class);
	public AdminDaoImpl() {
	}
	
	@Autowired
	private UserRepositroy repo;
	
	public List<User> getActiveUsers(Boolean status,Role role){
		logger.info("inside AdminDaoImpl.getActiveUsers()");
		return repo.getActiveUsers(status,role);
	}
	
	public List<User> getInactiveUsers(Boolean status,Role role){
		logger.info("inside AdminDaoImpl.getInactiveUsers()");
		return repo.getInactiveUsers(status,role);
	}

	public List<Shouts> getReportedShouts(@Param("p") Boolean flag){
		logger.info("inside AdminDaoImpl.getReportedShouts()");
		return repo.getReportedShouts(flag);
	}
	
	public int deleteUser(Boolean isUserActive,Boolean isStatusActive,Integer id1,Integer id2){
		logger.info("inside AdminDaoImpl.deleteUser()");
		return repo.deleteUser(isUserActive,isStatusActive,id1,id2);
	}
	
	public int deleteShouts(Boolean shoutsInactive,Integer id){
		logger.info("inside AdminDaoImpl.deleteShouts()");
		return repo.deleteShouts(shoutsInactive,id);
	}
	
	public int approveUser(Boolean isUserActive,Integer id){
		logger.info("inside AdminDaoImpl.approveUser()");
		return repo.approveUser(isUserActive,id);
	}
	
}
