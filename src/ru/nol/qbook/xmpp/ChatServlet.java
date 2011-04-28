package ru.nol.qbook.xmpp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.nol.qbook.lingu.Dictionary;
import ru.nol.qbook.model.ChatRegistry;
import ru.nol.qbook.model.ChatSubscription;
import ru.nol.qbook.persistence.Chat;
import ru.nol.qbook.persistence.DuplicateJIDException;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;

public class ChatServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5741776785010604372L;
	static final Dictionary dict = new Dictionary();
	static {
		dict.addWord("слово", "слова", "слов");
		dict.addWord("знак", "знака", "знаков");
	}
	
	private static final Logger log = Logger.getLogger(ChatServlet.class.getName());


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final XMPPService xmpp = XMPPServiceFactory.getXMPPService();
		final Message message = xmpp.parseMessage(req);
		final String chatName = readChatName(message);
		logPost(message, chatName);
		final Chat chat = ChatRegistry.getInstance().getChat(chatName);
		subscribeNewJID(message, chat);
		final String redirectBody = getRedirectBody(message, chat);
		final JID[] jIDs = getRedirectJIDs(message, chat);
		


		xmpp.sendMessage(new MessageBuilder().withBody(redirectBody)
				.withFromJid(message.getRecipientJids()[0])
				.withRecipientJids(jIDs).build());

		super.doPost(req, resp);
	}

	private void logPost(Message message, String chatName) {
			log.info(	"from: " +  message.getFromJid()+ "\n"
					+	"to: " + Arrays.toString(message.getRecipientJids()) + "\n"
					+	"chat: " + chatName + "\n");
		
	}

	private void subscribeNewJID(Message message, Chat chat) {
		final ChatSubscription subscription = chat.addSubscription();
		try {
			subscription.setJID(message.getFromJid().getId());
		} catch (DuplicateJIDException e) {
		}
	}

	private JID[] getRedirectJIDs(Message message, Chat chat) {
		final List<JID> jIDs = new ArrayList<JID>();
		final Collection<ChatSubscription> subscriptions = chat
				.getSubscriptions();
		for (final ChatSubscription subscription : subscriptions) {
			if (!subscription.getJID().equals(message.getFromJid().getId()))
				jIDs.add(new JID(subscription.getJID()));
		}
		return jIDs.toArray(new JID[0]);
	}

	private String getRedirectBody(Message message, Chat chat) {
		return "<"
				+ chat.getSubscription(message.getFromJid().getId()).getAlias()
				+ ">:" + message.getBody();
	}

	private String readChatName(Message message) {
		return message.getRecipientJids()[0].getId().split("@")[0];
	}

	private String answer(String msg) {
		final int words = msg.equals("") ? 0 : msg.split("\\s+").length;
		final int letters = msg.length();

		return String
				.format("Приветствую Вас, джентельмен. Ваше предложение содержит %d %s и %d %s ?",
						words, dict.getForm("слово", words), letters,
						dict.getForm("знак", letters));
	}

	public static void main(String[] args) {
		System.out.println(new ChatServlet().answer("1234567890123"));
	}

}
