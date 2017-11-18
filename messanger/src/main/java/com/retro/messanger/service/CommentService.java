package com.retro.messanger.service;

import java.util.List;

import com.retro.messanger.model.Comment;
import com.retro.messanger.repository.CommentRepository;
import com.retro.messanger.service.mapper.CommentMapper;

public class CommentService {
	CommentRepository repository;
	CommentMapper mapper;

	public CommentService() {
		super();
		repository = new CommentRepository();
		mapper = new CommentMapper();
	}

	public List<Comment> getAllComments(long messageId) {
		return mapper.map(repository.getAllComments(messageId));
	}

	public Comment getComment(long messageId, long commentId) {
		return mapper.map(repository.getComment(messageId, commentId));
	}

	public Comment addComment(long messageId, Comment comment) {
		return mapper.map(repository.save(messageId, mapper.map(comment)));
	}

	public Comment updateComment(long messageId, long commentId, Comment comment) {
		return mapper.map(repository.update(messageId, mapper.map(comment)));
	}

	public Comment deleteComment(long messageId, long commentId) {
		return mapper.map(repository.delete(messageId, commentId));
	}

}
