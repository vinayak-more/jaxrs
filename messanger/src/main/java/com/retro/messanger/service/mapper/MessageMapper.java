package com.retro.messanger.service.mapper;

import static java.util.Collections.emptyList;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.retro.messanger.dao.MessageEntity;
import com.retro.messanger.model.Message;

public class MessageMapper {

	public Message map(MessageEntity messageEntity) {
		if (messageEntity == null) {
			return null;
		}
		Message message = new Message();
		message.setId(messageEntity.getId());
		message.setMessage(messageEntity.getMessage());
		message.setAuthor(messageEntity.getAuthor());
		message.setCreated(messageEntity.getCreated());
		return message;
	}

	public MessageEntity map(Message message) {
		if (message == null) {
			return null;
		}
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setId(message.getId());
		messageEntity.setMessage(message.getMessage());
		messageEntity.setAuthor(message.getAuthor());
		messageEntity.setCreated(message.getCreated());
		return messageEntity;
	}

	public List<Message> map(List<MessageEntity> messageEntities) {
		if (CollectionUtils.isEmpty(messageEntities)) {
			return emptyList();
		}
		return messageEntities.stream().map(e -> map(e)).collect(Collectors.toList());

	}

	public List<MessageEntity> mapToEntity(List<Message> messages) {
		if (CollectionUtils.isEmpty(messages)) {
			return emptyList();
		}
		return messages.stream().map(e -> map(e)).collect(Collectors.toList());
	}

}
