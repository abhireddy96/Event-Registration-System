package com.abhi.reddy.exceptions;

public class EmployeeNotFoundException extends Exception {
	
	/**
	 * Instantiates a new employee not found exception.
	 *
	 * @param message the message
	 */
	public EmployeeNotFoundException(String message) {
		super(message);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6405980652584416229L;

}
