package ru.nol.qbook.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import ru.nol.qbook.model.ChatSubscription;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Chat {

	@PrimaryKey
	@Persistent
	private String name;

	@Persistent(serialized="true")
	private Collection<Subscription> subscriptions = new ArrayList<Subscription>();

	@NotPersistent
	private Map<String, ChatSubscription> subscriptionMap;

	public String getName() {
		return name;
	}

	public ChatSubscription getSubscription(String JID) {
		return getSubscriptionMap().get(JID);
	}

	public Chat(String name) {
		this.subscriptions = new ArrayList<Chat.Subscription>();
		System.out.println(this.subscriptions);
		this.name = name;
	}

	public ChatSubscription addSubscription() {
		return new Subscription();
	}

	public Collection<ChatSubscription> getSubscriptions() {
		return getSubscriptionMap().values();
	}

	protected Map<String, ChatSubscription> getSubscriptionMap() {
		if (this.subscriptionMap == null) {
			this.subscriptionMap = new HashMap<String, ChatSubscription>();
			for (ChatSubscription subscription : this.subscriptions) {
				this.subscriptionMap.put(subscription.getJID(), subscription);
			}
		}
		return this.subscriptionMap;
	}

	class Subscription implements Serializable, ChatSubscription {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5913185228821936349L;
		private String jID = null;
		private String alias = null;

		@Override
		public final String getJID() {
			return jID;
		}

		@Override
		public void setJID(String jID) throws DuplicateJIDException {
			if (this.jID != null) {
				if (this.jID.equals(jID))
					return;
				if (Chat.this.getSubscriptionMap().containsKey(jID))
					throw new DuplicateJIDException(jID);
				Chat.this.getSubscriptionMap().remove(this.jID);
			} else
//				System.out.println(Chat.this.subscriptions);
				Chat.this.subscriptions.add(this);
			this.jID = jID;
			Chat.this.getSubscriptionMap().put(this.jID, this);
			if (this.alias == null) {
				setAlias(jID.split("@")[0]);
			}
		}

		@Override
		public String getAlias() {
			return alias;
		}

		@Override
		public void setAlias(String alias) {
			this.alias = alias;
		}
	}

}
