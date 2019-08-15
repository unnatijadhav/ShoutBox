package com.app.shoutbox.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.shoutbox.model.Shouts;
import com.app.shoutbox.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@Autowired
	private UserRepositroy repo;
	
	@Override
	public Integer verifyMail(String mail){
		return repo.verifyMail(mail);
	}

	@Override
	public User getUserById(int id) {
		return repo.findOne(id);
	}

	@Override
	public List<Shouts> getCurrentUserShouts(User user) {
		return repo.getCurrentUserShouts(user);
	}

	@Override
	public User getCurrentUserById(Integer currentUserId) {
		return repo.findOne(currentUserId);
	}

	@Override
	public User loginUser(User u){
		User u1 = repo.findUserByEmailandPassword(u.getEmail(),u.getPassword());
		return u1;
	}
	
	@Override
	public User registerUser(User u) {
		return repo.save(u);
	}
	
}
