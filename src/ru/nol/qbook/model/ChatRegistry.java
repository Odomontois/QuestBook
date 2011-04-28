package ru.nol.qbook.model;

import java.util.HashMap;
import java.util.Map;

import javax.jdo.PersistenceManager;

import ru.nol.qbook.persistence.Chat;
import ru.nol.qbook.persistence.PMF;

public final class ChatRegistry {
	private Map<String, Chat> chatCache = new HashMap<String, Chat>();

	private ChatRegistry() {
	};

	private PersistenceManager pm;

	static public ChatRegistry getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public Chat getChat(String name) {
		Chat chat = this.chatCache.get(name);
		if (chat == null)
			chat = readChat(name);
		if (chat == null)
			chat = createChat(name);
		return chat;
	}

	private Chat createChat(String name) {
		final Chat chat = new Chat(name);
		getPersistenceManager().makePersistent(chat);
		chatCache.put(name, chat);
		return chat;
	}

	private Chat readChat(String name) {
		final PersistenceManager pm = PMF.get().getPersistenceManager();
		final Chat chat = getPersistenceManager().getObjectById(Chat.class,
				name);
		if(chat == null ) return chat;
		System.out.println("chat readed" + chat);
		chatCache.put(name, chat);
		return chat;

	}

	private PersistenceManager getPersistenceManager() {
		if (this.pm == null)
			this.pm = PMF.get().getPersistenceManager();
		return this.pm;
	}

	public void save() {
		if (this.pm == null)
			return;
		this.pm.close();
		System.out.println("saved");
		this.pm = null;
	}

	static private class InstanceHolder {
		final private static ChatRegistry INSTANCE = new ChatRegistry();
	}
}