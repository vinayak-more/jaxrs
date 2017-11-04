package com.retro.messanger.database;

import java.util.HashMap;
import java.util.Map;

import com.retro.messanger.model.Comment;
import com.retro.messanger.model.Message;
import com.retro.messanger.model.Profile;

public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<Long, Message>();
	private static Map<Long, Profile> profiles = new HashMap<Long, Profile>();

	static {
		Message message = new Message(1, "Hello World!", "Vinayak");
		message.getComments().put(11L, new Comment(11L,"Wonderfull","Vijay"));
		message.getComments().put(12L, new Comment(12L,"Awesome","Rahul"));
		messages.put(1L, message);
		Message message2 = new Message(2, "Hello Jersey!", "Vinayak");
		message2.getComments().put(21L, new Comment(21L,"You did it!!","Supriya"));
		message2.getComments().put(22L, new Comment(22L,"Good efforts !!!!","Ashutosh"));
		messages.put(2L, message2);
	}
	public static Map<Long, Message> getAllMessages() {
		return messages;
	}

	public static Map<Long, Profile> getAllProfiles() {
		return profiles;
	}

}
