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
 * add a product once the system is ready to dispense the products.
 *
 */
public class AddWhileDispensingException extends IncorrectStateException {
	private static final long serialVersionUID = 1L;

	public AddWhileDispensingException() {
		super();
	}

	public AddWhileDispensingException(String message) {
		super(message);
	}

	public AddWhileDispensingException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddWhileDispensingException(Throwable cause) {
		super(cause);
	}

}