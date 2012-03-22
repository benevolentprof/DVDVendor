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
 * An exception which is thrown when a scanned product does not
 * exist in the product database. 
 *
 */
public class InvalidProductException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidProductException() {
		super();
	}

	public InvalidProductException(String message) {
		super(message);
	}

	public InvalidProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidProductException(Throwable cause) {
		super(cause);
	}

}
