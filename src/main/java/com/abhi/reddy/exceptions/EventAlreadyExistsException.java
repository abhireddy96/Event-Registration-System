package com.abhi.reddy.exceptions;

public class EventAlreadyExistsException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7930196190344431611L;

	/**
	 * Instantiates a new event already exists exception.
	 *
	 * @param message
	 *            the message
	 */
	public EventAlreadyExistsException(String message) {
		super(message);
	}

}
