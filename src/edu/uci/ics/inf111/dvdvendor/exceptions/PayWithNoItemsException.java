/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 18, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */

package edu.uci.ics.inf111.dvdvendor.exceptions;


/**
 * A simple exception which is thrown when a customer tries to 
 * pay and there are no items added to its CheckOutCart.
 *
 */
public class PayWithNoItemsException extends IncorrectStateException {
	private static final long serialVersionUID = 1L;

	public PayWithNoItemsException() {
		super();
	}

	public PayWithNoItemsException(String message) {
		super(message);
	}

	public PayWithNoItemsException(String message, Throwable cause) {
		super(message, cause);
	}

	public PayWithNoItemsException(Throwable cause) {
		super(cause);
	}

}