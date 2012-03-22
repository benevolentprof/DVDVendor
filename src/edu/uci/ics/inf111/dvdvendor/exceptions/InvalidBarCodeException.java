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
 * An exception which is thrown when a Bar Code is not valid.
 *
 */

public class InvalidBarCodeException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidBarCodeException() {
		super();
	}

	public InvalidBarCodeException(String message) {
		super(message);
	}

	public InvalidBarCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidBarCodeException(Throwable cause) {
		super(cause);
	}

}