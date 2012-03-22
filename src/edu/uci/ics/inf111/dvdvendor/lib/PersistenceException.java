/*
 * Creator: Rosalva Gallardo-Valencia
 * Course: Inf111, Winter 2009
 * 
 * Created on January 14, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */

package edu.uci.ics.inf111.dvdvendor.lib;
/**
 * An exception which is thrown when the Persistence device fails
 * because the file path is not valid, the data to print is null or contain nulls, or
 * there was an error writing on the output file.
 */
public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;
	public PersistenceException() {
		super();
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}
}
