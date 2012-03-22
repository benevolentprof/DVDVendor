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
 * rent a product that is already rented.
 *
 */
public class ProductAlreadyRentedException extends IncorrectStateException {
	private static final long serialVersionUID = 1L;

	public ProductAlreadyRentedException() {
		super();
	}

	public ProductAlreadyRentedException(String message) {
		super(message);
	}

	public ProductAlreadyRentedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductAlreadyRentedException(Throwable cause) {
		super(cause);
	}

}