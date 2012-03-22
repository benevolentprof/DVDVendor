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
 * calculate the charges in a return transaction when there are no received items.
 *
 */
public class CalculateWithNoItemException extends IncorrectStateException {
	private static final long serialVersionUID = 1L;

	public CalculateWithNoItemException() {
		super();
	}

	public CalculateWithNoItemException(String message) {
		super(message);
	}

	public CalculateWithNoItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public CalculateWithNoItemException(Throwable cause) {
		super(cause);
	}

}