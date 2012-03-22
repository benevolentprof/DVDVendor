/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 8, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */
package edu.uci.ics.inf111.dvdvendor.app;
/**
 * The Product interface is implemented by any class which represents a rentable product
 * in our point of sale. This interface has been created to simplify the creation
 * of future products to be rented or sold. In our example, DVD implements this interface.
 * The interface provides common accessor methods for the fields.
 *
 */
public interface Product {

	/**
	 * Accessor method for identifying the bar code
	 */
	public BarCode getBarCode();

	/**
	 * Accessor method for unit price
	 */
	public double getPrice();
	
}
