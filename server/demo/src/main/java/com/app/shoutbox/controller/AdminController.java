
package com.app.shoutbox.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shoutbox.beans.ShoutBean;
import com.app.shoutbox.model.Role;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;
import com.app.shoutbox.service.AdminService;

/**
 * @author Administrator
 *
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("user")
public class AdminController {
	private Logger logger = LogManager.getLogger(AdminController.class);
	private Boolean statusActive=true;
	private Boolean statusInactive=false;
	private Boolean isReported=true;
	private Boolean shoutsInactive=false;
	private Role role = Role.USER;
	
	public AdminController() {
	}
	
	@Autowired
	private AdminService service;

	/**
	 * GET ACTIVE USERS (ABHIJIT ROKADE)
	 */
	@GetMapping("/active")
	public List<User> getActiveUsers(boolean status,Role role){
		role=this.role;
		logger.info("AdminController.getActiveUSers()");
		status=this.statusActive;
		return service.getActiveUsers(status,role);
	}
	
	/**
	 * GET INACTIVE USERS (ABHIJIT ROKADE)
	 */
	@GetMapping("/inactive")
	public List<User> getInactiveUsers(boolean status,Role role){
		role=this.role;
		logger.info("AdminController.getInactiveUSers()");
		status=this.statusInactive;
		return service.getInactiveUsers(status,role);
	}
	
	/**
	 * GET REPORTED SHOUTS (PRASHANT SHARMA)
	 */
	@GetMapping("/isreported")
	public Map<String,Object> getReportedShouts(boolean status){
		logger.info("AdminController.getReportedShouts()");
		
		Map<String, Object> shoutMap = new HashMap<>(); 
		List<Shouts> allShouts = new ArrayList<Shouts>();
		List<ShoutBean> allShoutsBean = new ArrayList<ShoutBean>();
		
		try 
		{
			List<Shouts> tempList = service.getReportedShouts();
			for (Shouts shouts : tempList) {
				if (shouts.getShoutContentType().equals("text")) {
					
					ShoutBean sb = new ShoutBean();
					sb.setId(shouts.getId());
					sb.setTimestamp(shouts.getTimestamp());
					sb.setShoutContentType(shouts.getShoutContentType());
					sb.setShoutActive(shouts.isShoutActive());
					sb.setData(new String(shouts.getData()));
					sb.setOwner(shouts.getOwner());
					
					allShoutsBean.add(sb);
					shoutMap.put("textShouts", allShoutsBean);
					
				} else {
					
					Shouts tempShout = new Shouts();
					tempShout.setId(shouts.getId());
					tempShout.setTimestamp(shouts.getTimestamp());
					tempShout.setShoutContentType(shouts.getShoutContentType());
					tempShout.setShoutActive(shouts.isShoutActive());
					tempShout.setData(shouts.getData());
					tempShout.setOwner(shouts.getOwner());
					
					allShouts.add(tempShout);
					shoutMap.put("blobShouts", allShouts);
				}
			}	// end of for-each loop
		 
			return shoutMap;
			
		}catch(Exception e){
			return null; 
		}
	}
	
	
	
	/**
	 * DELETE USER BY USER ID
	 */
	@GetMapping("/deleteuser/{id}")
	public int deleteUser( @PathVariable("id")int userId){
		logger.info("AdminController.deleteUser()");
		service.deleteShout(shoutsInactive, userId);
		return service.deleteUser(statusInactive,userId);
	}
	
	
	/**
	 * DELETE SHOUT BY SHOUT ID (ABHIJIT ROKADE)
	 */
	@GetMapping("/deleteshouts/{id}")
	public int deleteShouts(@PathVariable("id")int id){
		logger.info("AdminController.deleteShouts()");
		return service.deleteShouts(shoutsInactive,id);
	}
	
	/**
	 * APPROVE NEWLY REGISTERED USERS (ABHIJIT ROKADE)
	 */
	@GetMapping("/approve/{id}")
	public int approveUser(@PathVariable("id")int id){
		logger.info("AdminController.approveUser()");
		return service.approveUser(statusActive,id);
	}
	
	
	
}
