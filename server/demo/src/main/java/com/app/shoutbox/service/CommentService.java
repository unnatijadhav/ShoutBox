package com.app.shoutbox.service;

import java.util.List;

import com.app.shoutbox.model.Comments;
import com.app.shoutbox.model.Shouts;

public interface CommentService {
	public Comments postComment(Comments comment);

	public List<Comments> getCommentsByShout(Shouts currentShout);
}
