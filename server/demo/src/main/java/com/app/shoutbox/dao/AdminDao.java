package com.app.shoutbox.dao;

import java.util.List;

import com.app.shoutbox.model.Role;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

public interface AdminDao {

	public List<User> getActiveUsers(Boolean status,Role role);
	public List<User> getInactiveUsers(Boolean status,Role role);
	public List<Shouts> getReportedShouts();
	public int deleteUser(Boolean isUserActive,Integer id);
	public int deleteShouts(Boolean shoutsInactive,Integer id);
	public int deleteShout(Boolean shoutsInactive,Integer id);
	public int approveUser(Boolean isUserActive,Integer id);
}
