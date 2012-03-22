/*
 * Creator: Rosalva Gallardo-Valencia
 * Course: Inf111, Winter 2009
 * 
 * Created on January 8, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */
package edu.uci.ics.inf111.dvdvendor.app;

import edu.uci.ics.inf111.dvdvendor.exceptions.InvalidBarCodeException;
/**
 * This class represents a Return Transaction in the point of sale. It
 * contains the main characteristics of a Return Transaction and it extends
 * the abstract class Transaction
 *
 */
public class ReturnTransaction extends Transaction{

	/**
	 * The bar code for the transaction. 
	 */
	private BarCode barCode;
	
	/**
	 * Constructor that returns a Return Transaction with blank values
	 */
	public ReturnTransaction() {
		this.setDateTime("");
	
	}	
	
	/**
	 * Constructor that assigns values based on the information on the TransactionDB
	 * @param line 	String that represents a line in the TransactionDB file
	 * @throws InvalidBarCodeException if the BarCode read is not valid
	 */	
	public ReturnTransaction(String line) throws InvalidBarCodeException {
		String symbol = "|";
		
		//Reading Date and Time
		super.setDateTime(Utils.getFieldBeforeSymbol(line, symbol));
		line = Utils.getLineAfterSymbol(line, "|");
		
		//Reading Cost
		this.setCost(Double.valueOf(Utils.getFieldBeforeSymbol(line, symbol)).doubleValue());
		line = Utils.getLineAfterSymbol(line, "|");
		
		//Reading Card Number
		this.setCardNumber(Utils.getFieldBeforeSymbol(line, symbol));
		line = Utils.getLineAfterSymbol(line, "|");
		
		//Reading Bar Code
		this.setBarCode(new BarCode(Utils.getFieldBeforeSymbol(line, symbol)));
	
	}
	
	/**
	 * An accessor method which sets the BarCode for the transaction.
	 */
	public void setBarCode(BarCode barCode) {
		this.barCode = barCode;
	}
	
	/**
	 * An accessor method which returns the BarCode for the transaction.
	 */
	public BarCode getBarCode() {
		return barCode;
	}
	
	/**
	 * Implementation of the abstract method to define the toString action. 
	 * This method will take the values of the ReturnTransaction class and
	 * will concatenate them in a String with the expected format in the 
	 * Transaction database.
	 * Format: TypeOfTransaction|DateTime|Cost|CardNumber|BarCode|
	 * @return String Represents the transaction to be stored in the Transaction
	 * database.
	 */	
	public String toString() {
		String details;
		details = "RETURN" + "|" + this.getDateTime() + "|" + this.getCost() + "|" + this.getCardNumber() + "|" + this.barCode.getBarCode() + "|";
		
		return details;
	}
}
