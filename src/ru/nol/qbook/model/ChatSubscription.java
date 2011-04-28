package ru.nol.qbook.model;

import ru.nol.qbook.persistence.DuplicateJIDException;

public interface ChatSubscription {

	String getJID();

	void setJID(String jID) throws DuplicateJIDException;

	String getAlias();

	void setAlias(String alias);

}