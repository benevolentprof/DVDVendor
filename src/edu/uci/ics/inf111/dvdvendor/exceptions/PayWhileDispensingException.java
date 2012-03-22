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
 * pay once the system is ready to dispense the products.
 *
 */
public class PayWhileDispensingException extends IncorrectStateException {
	private static final long serialVersionUID = 1L;

	public PayWhileDispensingException() {
		super();
	}

	public PayWhileDispensingException(String message) {
		super(message);
	}

	public PayWhileDispensingException(String message, Throwable cause) {
		super(message, cause);
	}

	public PayWhileDispensingException(Throwable cause) {
		super(cause);
	}

}