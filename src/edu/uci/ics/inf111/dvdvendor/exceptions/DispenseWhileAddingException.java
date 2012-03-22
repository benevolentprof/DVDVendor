/*
 * Creator: Rosalva Gallardo-Valencia
 * Course: Inf111, Winter 2009
 * 
 * Created on January 17, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */

package edu.uci.ics.inf111.dvdvendor.exceptions;


/**
 * A simple exception which is thrown when a customer tries to 
 * dispense a product once he/she is still adding products.
 *
 */
public class DispenseWhileAddingException extends IncorrectStateException {
	private static final long serialVersionUID = 1L;

	public DispenseWhileAddingException() {
		super();
	}

	public DispenseWhileAddingException(String message) {
		super(message);
	}

	public DispenseWhileAddingException(String message, Throwable cause) {
		super(message, cause);
	}

	public DispenseWhileAddingException(Throwable cause) {
		super(cause);
	}

}