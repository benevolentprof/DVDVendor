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
 * The IncorrectStateException acts as a parent
 * class for those exceptions which represent violations of the business rules of
 * the states a DVDVendor object can be in. It is used directly in some cases.
 *
 */
public class IncorrectStateException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncorrectStateException() {
		super();
	}

	public IncorrectStateException(String message) {
		super(message);
	}

	public IncorrectStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectStateException(Throwable cause) {
		super(cause);
	}

}
