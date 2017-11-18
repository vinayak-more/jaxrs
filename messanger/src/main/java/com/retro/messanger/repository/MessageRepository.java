package com.retro.messanger.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.retro.messanger.configuration.HibernateSessionManager;
import com.retro.messanger.dao.MessageEntity;

public class MessageRepository {

	@SuppressWarnings("unchecked")
	public List<MessageEntity> getAllMessages() {
		Session session = HibernateSessionManager.getSession();
		Query query = session.createQuery("FROM MessageEntity");
		List<MessageEntity> messages = query.list();
		session.close();
		return messages;
	}

	public MessageEntity getMessage(long messageId) {
		Session session = HibernateSessionManager.getSession();
		MessageEntity messageEntity = (MessageEntity) session.get(MessageEntity.class,
				messageId);
		session.close();
		return messageEntity;
	}

	public MessageEntity save(MessageEntity message) {
		Session session = HibernateSessionManager.getSession();
		try {
			session.beginTransaction();
			session.save(message);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return null;
		}finally{
			session.close();
		}
		return message;
	}

	public MessageEntity update(MessageEntity messageEntity) {
		Session session = HibernateSessionManager.getSession();
		try {
			session.beginTransaction();
			session.update(messageEntity);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			return null;
		}finally{
			session.close();
		}
		return messageEntity;
	}

	public MessageEntity delete(long Id) {
		Session session = HibernateSessionManager.getSession();
		MessageEntity messageEntity = null;
		try {
			session.beginTransaction();
			messageEntity = (MessageEntity) session
					.get(MessageEntity.class, Id);
			session.delete(messageEntity);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
		}finally{
			session.close();
		}
		return messageEntity;

	}

}
