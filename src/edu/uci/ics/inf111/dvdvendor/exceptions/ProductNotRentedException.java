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
 * return a product that has not been rented.
 *
 */
public class ProductNotRentedException extends IncorrectStateException {
	private static final long serialVersionUID = 1L;

	public ProductNotRentedException() {
		super();
	}

	public ProductNotRentedException(String message) {
		super(message);
	}

	public ProductNotRentedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNotRentedException(Throwable cause) {
		super(cause);
	}

}