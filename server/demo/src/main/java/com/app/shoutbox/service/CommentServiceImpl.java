package com.app.shoutbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shoutbox.dao.CommentRepository;
import com.app.shoutbox.model.Comments;
import com.app.shoutbox.model.Shouts;


@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepo;
	
	@Override
	public Comments postComment(Comments comment) {
		return commentRepo.save(comment);
	}

	@Override
	public List<Comments> getCommentsByShout(Shouts currentShout) {
		return commentRepo.getCommentsByShoutAndIsUserActive(currentShout);
	}

}
