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
 * An exception which is thrown when the database of Products 
 * fails
 */

public class ProductDBException extends Exception {

	private static final long serialVersionUID = 1L;
	public ProductDBException() {
		super();
	}

	public ProductDBException(String message) {
		super(message);
	}

	public ProductDBException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductDBException(Throwable cause) {
		super(cause);
	}
}
