package ru.nol.qbook.persistence;

/**
 * Occurs when chat name already exists in registry
 * @author Oleg Nizhnikov
 *
 */
public class DuplicateJIDException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8444347765360863641L;
	private final String jID;
	public DuplicateJIDException(String jID) {
		super();
		this.jID = jID;
	}
	public String getjID() {
		return jID;
	}
	@Override
	public String toString() {
		return "DuplicateJIDException [jID=" + jID + "]";
	}
	

}
