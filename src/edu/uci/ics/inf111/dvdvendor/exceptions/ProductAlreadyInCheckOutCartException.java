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
 * add a product that is already in the CheckOutCart.
 *
 */
public class ProductAlreadyInCheckOutCartException extends IncorrectStateException {
	private static final long serialVersionUID = 1L;

	public ProductAlreadyInCheckOutCartException() {
		super();
	}

	public ProductAlreadyInCheckOutCartException(String message) {
		super(message);
	}

	public ProductAlreadyInCheckOutCartException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductAlreadyInCheckOutCartException(Throwable cause) {
		super(cause);
	}

}