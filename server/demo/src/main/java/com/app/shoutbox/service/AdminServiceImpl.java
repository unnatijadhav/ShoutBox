package com.app.shoutbox.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.app.shoutbox.dao.AdminDao;
import com.app.shoutbox.model.Role;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

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
	
	public List<Shouts> getReportedShouts(){
		logger.info("inside AdminServiceImpl.getReportedShouts()");
		return dao.getReportedShouts();
	}
	
	public int deleteUser(Boolean isUserActive,Integer id){
		logger.info("AdminServiceImpl.deleteUser()");
		return dao.deleteUser(isUserActive, id);
	}
	
	public int deleteShouts(Boolean shoutsInactive,Integer id){
		logger.info("AdminServiceImpl.deleteShouts()");
		return dao.deleteShouts(shoutsInactive,id);
	}
	
	public int deleteShout(Boolean shoutsInactive,Integer id){
		logger.info("AdminServiceImpl.deleteShout()");
		return dao.deleteShout(shoutsInactive,id);
	}

	public int approveUser(Boolean isUserActive,Integer id){
		logger.info("AdminServiceImpl.approveUser()");
		return dao.approveUser(isUserActive,id);
	}

	
}
