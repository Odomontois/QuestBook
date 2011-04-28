package ru.nol.qbook.persistence;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Some {
	@PrimaryKey
	@Persistent
	private String id;
	
	@Persistent
	private String value;

	public String getId() {
		return id;
	}

	public Some(String id) {
		super();
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
