package com.gcu.exception;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * Custom exception to hid our technologies when an error is thrown
 */

public class DatabaseException extends RuntimeException {

	/**
	 * Set a unique ID for the customException
	 */
	private static final long serialVersionUID = 7875269707011194103L;
	
	/**
	 * Constructor to take in a stack trace in order to find out where the error came from
	 * @param e - Throwable - the stack trace of the error
	 */
	public DatabaseException(Throwable e)
	{
		super(e);
	}

}
