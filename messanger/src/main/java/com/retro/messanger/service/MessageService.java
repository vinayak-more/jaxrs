package com.retro.messanger.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.retro.messanger.model.Message;
import com.retro.messanger.model.MessageFilter;
import com.retro.messanger.repository.MessageRepository;
import com.retro.messanger.service.mapper.MessageMapper;
import com.retro.messanger.service.predicate.MessagePredicate;

public class MessageService {

	private MessageRepository repository;
	private MessageMapper mapper;

	public MessageService() {
		this.repository = new MessageRepository();
		mapper = new MessageMapper();
	}

	public List<Message> getAllMessages(MessageFilter filter) {
		List<Message> messagesToFilter = mapper
				.map(repository.getAllMessages());
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

	public Message getMessage(long messageId) {
		return mapper.map(repository.getMessage(messageId));
	}

	public Message addMessage(Message message) {
		message.setCreated(new Date());
		return mapper.map(repository.save(mapper.map(message)));
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		return mapper.map(repository.update(mapper.map(message)));
	}

	public Message deleteMessage(long id) {
		return mapper.map(repository.delete(id));
	}

}
