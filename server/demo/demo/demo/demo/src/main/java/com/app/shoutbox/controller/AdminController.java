package com.app.shoutbox.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shoutbox.model.Role;
import com.app.shoutbox.model.User;
import com.app.shoutbox.service.AdminService;
import com.app.shoutbox.utilities.HibernateInitializer;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("user")
public class AdminController {
	Logger log=Logger.getLogger(HibernateInitializer.class);
	private Boolean statusActive=true;
	private Boolean statusInactive=false;
	private Boolean shoutsActive=true;
	private Boolean shoutsInactive=false;
	private Role role = Role.USER;
	public AdminController() {
	}
	
	@Autowired
	private AdminService service;

	@GetMapping("/active")
	public List<User> getActiveUsers(boolean status,Role role){
		role=this.role;
		log.info("AdminController.getActiveUSers()");
		status=this.statusActive;
		return service.getActiveUsers(status,role);
	}
	
	@GetMapping("/inactive")
	public List<User> getInactiveUsers(boolean status,Role role){
		role=this.role;
		log.info("AdminController.getInactiveUSers()");
		status=this.statusInactive;
		return service.getInactiveUsers(status,role);
	}
	
	@GetMapping("/deleteuser/{id}")
	public int deleteUser( @PathVariable("id")int id1,@PathVariable("id")int id2){
		log.info("AdminController.deleteUser()");
		return service.deleteUser(statusInactive,shoutsInactive,id1,id2);
	}
	
	@GetMapping("/deleteshouts/{id}")
	public int deleteShouts(@PathVariable("id")int id){
		log.info("AdminController.deleteShouts()");
		return service.deleteShouts(shoutsInactive,id);
	}
	
	@GetMapping("/approve/{id}")
	public int approveUser(@PathVariable("id")int id){
		log.info("AdminController.approveUser()");
		return service.approveUser(statusActive,id);
	}
	
}
