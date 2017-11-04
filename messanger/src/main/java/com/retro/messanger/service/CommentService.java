package com.retro.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.retro.messanger.database.DatabaseClass;
import com.retro.messanger.model.Comment;
import com.retro.messanger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getAllMessages();

	public List<Comment> getAllComments(long messageId) {
		Message message = messages.get(messageId);
		if (message != null) {
			return new ArrayList<Comment>(message.getComments().values());
		}
		return null;
	}

	public Comment getComment(long messageId, long commentId) {
		Message message = messages.get(messageId);
		if (message != null) {
			return message.getComments().get(commentId);
		}
		return null;
	}

	public Comment addComment(long messageId, Comment comment) {
		Message message = messages.get(messageId);
		if (message != null) {
			comment.setId(message.getComments().size() + 1);
			message.getComments().put(comment.getId(), comment);
			return comment;
		} else {
			return null;
		}
	}

	public Comment updateComment(long messageId, long commentId, Comment comment) {
		Message message = messages.get(messageId);
		if (message != null && message.getComments().containsKey(commentId)) {
			comment.setId(commentId);
			message.getComments().put(comment.getId(), comment);
			return comment;
		} else {
			return null;
		}
	}

	public Comment deleteComment(long messageId, long commentId) {
		Message message = messages.get(messageId);
		if (message != null && message.getComments().containsKey(commentId)) {
			return message.getComments().remove(commentId);
		} else {
			return null;
		}
	}

}
