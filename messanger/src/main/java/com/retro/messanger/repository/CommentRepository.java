package com.retro.messanger.repository;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.retro.messanger.configuration.HibernateSessionManager;
import com.retro.messanger.dao.CommentEntity;
import com.retro.messanger.dao.MessageEntity;

public class CommentRepository {

	public List<CommentEntity> getAllComments(long messageId) {
		if (messageId <= 0) {
			return null;
		}
		List<CommentEntity> commentEntities = null;
		Session session = HibernateSessionManager.getSession();
		MessageEntity messageEntity = (MessageEntity) session.get(
				MessageEntity.class, messageId);
		if (messageEntity != null) {
			commentEntities = messageEntity.getComments();
			Hibernate.initialize(commentEntities);
		}
		session.close();
		return commentEntities;
	}

	public CommentEntity getComment(long messageId, long commentId) {
		if (messageId <= 0 || commentId <= 0) {
			return null;
		}
		Session session = HibernateSessionManager.getSession();
		try {
			MessageEntity messageEntity = (MessageEntity) session.get(
					MessageEntity.class, messageId);
			if (messageEntity == null) {
				return null;
			}
			CommentEntity commentEntity = messageEntity.getComments().stream()
					.filter(e -> e.getId() == commentId).findFirst().get();
			return commentEntity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public CommentEntity save(long messageId, CommentEntity commentEntity) {
		if (messageId <= 0 || commentEntity == null) {
			return null;
		}
		Session session = HibernateSessionManager.getSession();
		try {
			session.beginTransaction();
			MessageEntity messageEntity = (MessageEntity) session.get(
					MessageEntity.class, messageId);
			if (messageEntity == null) {
				return null;
			}
			messageEntity.getComments().add(commentEntity);
			session.getTransaction().commit();
			return commentEntity;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public CommentEntity update(long messageId, CommentEntity commentEntity) {
		if (messageId <= 0 || commentEntity == null) {
			return null;
		}
		Session session = HibernateSessionManager.getSession();
		try {
			session.beginTransaction();
			MessageEntity messageEntity = (MessageEntity) session.get(
					MessageEntity.class, messageId);
			if (messageEntity == null) {
				return null;
			}
			if (messageEntity.getComments().contains(commentEntity)) {
				session.update(commentEntity);
			}
			session.getTransaction().commit();
			return commentEntity;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public CommentEntity delete(long messageId, long commentId) {
		if (messageId <= 0 || commentId <= 0) {
			return null;
		}
		Session session = HibernateSessionManager.getSession();
		try {
			session.beginTransaction();
			MessageEntity messageEntity = (MessageEntity) session.get(
					MessageEntity.class, messageId);
			if (messageEntity == null) {
				return null;
			}
			CommentEntity commentEntity = messageEntity.getComments().stream()
					.filter(e -> e.getId() == commentId).findFirst().get();
			session.delete(commentEntity);
			session.getTransaction().commit();
			return commentEntity;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
