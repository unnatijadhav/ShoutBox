package com.app.shoutbox.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.app.shoutbox.controller.AdminController;
import com.app.shoutbox.dao.AdminDao;
import com.app.shoutbox.model.Role;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;
import com.app.shoutbox.utilities.HibernateInitializer;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	private Logger logger = LogManager.getLogger(AdminServiceImpl.class);
	public AdminServiceImpl() {
	}
	
	@Autowired
	private AdminDao dao;
	

	public List<User> getActiveUsers(Boolean status,Role role){
		logger.info("AdminServiceImpl.getActiveUsers()");
		return dao.getActiveUsers(status,role);
	}
	
	public List<User> getInactiveUsers(Boolean status,Role role){
		logger.info("AdminServiceImpl.getInactiveUsers()");
		return dao.getInactiveUsers(status,role);
	}
	
	public List<Shouts> getReportedShouts(@Param("p") Boolean flag){
		logger.info("inside AdminServiceImpl.getReportedShouts()");
		return dao.getReportedShouts(flag);
	}
	
	public int deleteUser(Boolean isUserActive,Boolean isStatusActive,Integer id1,Integer id2){
		logger.info("AdminServiceImpl.deleteUser()");
		return dao.deleteUser(isUserActive,isStatusActive,id1,id2);
	}
	
	public int deleteShouts(Boolean shoutsInactive,Integer id){
		logger.info("AdminServiceImpl.deleteShouts()");
		return dao.deleteShouts(shoutsInactive,id);
	}

	public int approveUser(Boolean isUserActive,Integer id){
		logger.info("AdminServiceImpl.approveUser()");
		return dao.approveUser(isUserActive,id);
	}

	
}
