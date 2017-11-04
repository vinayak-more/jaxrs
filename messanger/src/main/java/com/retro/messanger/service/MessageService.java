package com.retro.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.retro.messanger.database.DatabaseClass;
import com.retro.messanger.model.Message;
import com.retro.messanger.model.MessageFilter;

public class MessageService {
	private Map<Long, Message> messages = DatabaseClass.getAllMessages();

	public List<Message> getAllMessages(MessageFilter filter) {
		List<Message> messagesToFilter = new ArrayList<Message>(
				messages.values());
		messagesToFilter = messagesToFilter.stream()
				.filter(new MessagePredicate(filter))
				.collect(Collectors.toList());
		if (messagesToFilter.isEmpty()) {
			return null;
		}
		int start = filter.getStart();
		if (start <= 0) {
			return messagesToFilter;
		}
		int size = filter.getSize();
		if (size <= 0) {
			return messagesToFilter.subList(start - 1, messagesToFilter.size());
		}
		if (start + size > messagesToFilter.size()) {
			return messagesToFilter.subList(start - 1, messagesToFilter.size());
		} else {
			return messagesToFilter.subList(start - 1, start + size);
		}
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
