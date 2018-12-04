package com.abhi.reddy.exceptions;

public class EmployeeAlreadyExistsException extends Exception {

	/**
	 * Instantiates a new employee already exists exception.
	 *
	 * @param message the message
	 */
	public EmployeeAlreadyExistsException(String message) {
		super(message);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4710019263771675265L;	
}
