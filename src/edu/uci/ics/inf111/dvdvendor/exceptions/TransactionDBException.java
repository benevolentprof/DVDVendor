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
 * An exception which is thrown when the database of Transactions fails
 * because the file path is not valid or
 * there was an error writing on the output file.
 */
public class TransactionDBException extends Exception {

	private static final long serialVersionUID = 1L;
	public TransactionDBException() {
		super();
	}

	public TransactionDBException(String message) {
		super(message);
	}

	public TransactionDBException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionDBException(Throwable cause) {
		super(cause);
	}
}
