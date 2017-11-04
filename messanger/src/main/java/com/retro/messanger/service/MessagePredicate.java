package com.retro.messanger.service;

import java.util.Calendar;
import java.util.function.Predicate;

import com.retro.messanger.model.Message;
import com.retro.messanger.model.MessageFilter;

public class MessagePredicate implements Predicate<Message> {

	private final MessageFilter filter;
	private Calendar c = Calendar.getInstance();

	public MessagePredicate(MessageFilter filter) {
		super();
		this.filter = filter;
	}

	@Override
	public boolean test(Message message) {
		boolean flag = true;
		if (filter.getYear() > 0) {
			c.setTime(message.getCreated());
			flag = c.get(Calendar.YEAR) == filter.getYear();
		}
		if (flag && filter.getAuthor() != null) {
			flag = message.getAuthor().equalsIgnoreCase(filter.getAuthor());
		}
		return flag;
	}

}
