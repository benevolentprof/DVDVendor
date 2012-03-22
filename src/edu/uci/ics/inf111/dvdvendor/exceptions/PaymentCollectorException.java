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
 * An exception which is thrown when there is a problem with the payment interface, 
 * which in this case is represented by the PaymentCollector class.
 *
 */
public class PaymentCollectorException extends Exception {
	private static final long serialVersionUID = 1L;

	public PaymentCollectorException() {
		super();
	}

	public PaymentCollectorException(String message) {
		super(message);
	}

	public PaymentCollectorException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentCollectorException(Throwable cause) {
		super(cause);
	}

}
