package com.retro.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.retro.messanger.database.DatabaseClass;
import com.retro.messanger.model.Message;

public class MessageService {
	private Map<Long, Message> messages = DatabaseClass.getAllMessages();

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public Message getMessage(long id) {
		return messages.get(id);
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message deleteMessage(long id) {
		return messages.remove(id);
	}

}
