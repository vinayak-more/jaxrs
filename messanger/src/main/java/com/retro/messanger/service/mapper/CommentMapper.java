package com.retro.messanger.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.retro.messanger.dao.CommentEntity;
import com.retro.messanger.model.Comment;

public class CommentMapper {

	public Comment map(CommentEntity commentEntity) {
		if (commentEntity == null) {
			return null;
		}
		Comment comment = new Comment();
		comment.setId(commentEntity.getId());
		comment.setComment(commentEntity.getComment());
		comment.setAuthor(commentEntity.getAuthor());
		return comment;
	}

	public CommentEntity map(Comment comment) {
		if (comment == null) {
			return null;
		}
		CommentEntity entity = new CommentEntity();
		entity.setId(comment.getId());
		entity.setComment(comment.getComment());
		entity.setAuthor(comment.getAuthor());
		return entity;
	}

	public List<Comment> map(List<CommentEntity> entities) {
		if (entities == null) {
			return null;
		}
		return entities.stream().map(e -> map(e)).collect(Collectors.toList());
	}

	public List<CommentEntity> mapToEntity(List<Comment> comments) {
		if (comments == null) {
			return null;
		}
		return comments.stream().map(e -> map(e)).collect(Collectors.toList());
	}

}
