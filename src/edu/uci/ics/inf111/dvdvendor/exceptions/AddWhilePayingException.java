/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 14, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */

package edu.uci.ics.inf111.dvdvendor.exceptions;


/**
 * A simple exception which is thrown when a customer tries to 
 * add a product once the system is already processing the payment.
 *
 */
public class AddWhilePayingException extends IncorrectStateException {
	private static final long serialVersionUID = 1L;

	public AddWhilePayingException() {
		super();
	}

	public AddWhilePayingException(String message) {
		super(message);
	}

	public AddWhilePayingException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddWhilePayingException(Throwable cause) {
		super(cause);
	}

}
