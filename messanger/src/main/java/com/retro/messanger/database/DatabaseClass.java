package com.retro.messanger.database;

import java.util.HashMap;
import java.util.Map;

import com.retro.messanger.model.Message;
import com.retro.messanger.model.Profile;

public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<Long, Message>();
	private static Map<Long, Profile> profiles = new HashMap<Long, Profile>();

	public static Map<Long, Message> getAllMessages() {
		return messages;
	}

	public static Map<Long, Profile> getAllProfiles() {
		return profiles;
	}

}
