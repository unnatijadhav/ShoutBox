package com.app.shoutbox.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.shoutbox.model.FriendRequestFlag;
import com.app.shoutbox.model.Friends;
import com.app.shoutbox.model.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao{

	@Autowired
	private FriendRepository repo;
	
	@Autowired
	private UserRepositroy helper;
	
	@Override
	public String sendFriendReq(int user_id_sender, int user_id_recevier) {
		
		//Sender side done
		Friends sender = repo.findOne(user_id_sender);
		User u = helper.findOne(user_id_recevier);
		sender.setFriend(u);
		sender.setFlag(FriendRequestFlag.PENDING);
		
		//Recevier side 
		Friends rec = repo.findOne(user_id_recevier);
		User u1 = helper.findOne(user_id_sender);
		rec.setFriend(u1);
		rec.setFlag(FriendRequestFlag.PENDING);
		System.out.println("INSIDE DAO @@@@@@@");
		User sender = helper.findOne(user_id_sender);
		System.out.println("USER Sender"+sender);
	
		System.out.println("INSIDE DAO @@@@@@@");
		User receiver = helper.findOne(user_id_recevier);
		System.out.println("USER Receiver"+receiver);
		
		
		
		//Sender side done
		System.out.println("inside dao "+user_id_sender);
//		Friends sender = u2.getOwner();
		
		//System.out.println("sender"+sender);
//		
//		User u = helper.findOne(user_id_recevier);
//		sender.setFriend(u);
//		sender.setFlag(FriendRequestFlag.PENDING);
//		
//		//Recevier side 
//		Friends rec = repo.findOne(user_id_recevier);
//		User u1 = helper.findOne(user_id_sender);
//		rec.setFriend(u1);
//		rec.setFlag(FriendRequestFlag.PENDING);
		return "Friend request";
	}

	
}
