package com.app.shoutbox.dao;

import java.util.List;

import com.app.shoutbox.model.Role;
import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

public interface AdminDao {

	public List<User> getActiveUsers(Boolean status,Role role);
	public List<User> getInactiveUsers(Boolean status,Role role);
	public List<Shouts> getReportedShouts(Boolean flag);
	public int deleteUser(Boolean isUserActive,Boolean isStatusActive,Integer id1,Integer id2);
	public int deleteShouts(Boolean shoutsInactive,Integer id);
	public int approveUser(Boolean isUserActive,Integer id);
}
