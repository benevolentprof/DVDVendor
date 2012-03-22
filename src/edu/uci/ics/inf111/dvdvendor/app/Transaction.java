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
 * The Transaction abstract class is extended by any class which represents a transaction
 * in our point of sale. This class has been created to simplify the creation
 * of future transaction. In our example, RentTransaction and ReturnTransaction
 * extend this class. The class provides common accessor methods 
 * for the fields.
 *
 */
public abstract class Transaction {

	/**
	 * The date and time for the transaction. 
	 */
	private String dateTime;

	/**
	 * The cost for the transaction. 
	 */
	private double cost;
	
	/**
	 * The card number for the transaction. 
	 */
	private String cardNumber;
	
	/**
	 * An accessor method which sets the date and time for the transaction.
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * An accessor method which returns the date and time for the transaction.
	 */
	public String getDateTime() {
		return dateTime;
	}
	
	/**
	 * An accessor method which sets the cost for the transaction.
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * An accessor method which returns the cost for the transaction.
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * An accessor method which sets the card number for the transaction.
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	/**
	 * An accessor method which returns the card number for the transaction.
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * Static method that creates a Rent or a Return Transaction based on the input
	 * String that represents a line in the transactionDB file
	 * @param line 	String that represents a line in the TransactionDB file
	 * @return Transaction An object with the created Transaction
	 * @throws InvalidBarCodeException if the BarCode read is not valid
	 */
	public static Transaction createTransaction(String line) throws InvalidBarCodeException
	{
		Transaction transaction = null;
		String symbol = "|";
		
		//Get the string between the beginning of the line and the symbol |
		String transactionType = Utils.getFieldBeforeSymbol(line, symbol);
		
		//Delete the read field that represents the type of transaction
		line = Utils.getLineAfterSymbol(line, symbol);
		
		// If the input String represents a Rent Transaction, then create a 
		// RentTransaction object
		if (transactionType.equals("RENT"))
		{
			transaction = new RentTransaction(line);
		}
		// If the input String represents a Return Transaction, then create a 
		// ReturnTransaction object
		else if (transactionType.equals("RETURN"))
		{
			transaction = new ReturnTransaction(line);
		}
		return transaction;				
	}
	/**
	 * Abstract method to define the toString method in each class that 
	 * extends this abstract class. This method will take the values of the class and
	 * will concatenate them in a String with the expected format in the 
	 * Transaction database.
	 * @return String Represents the transaction to be stored in the Transaction
	 * database.
	 */
	public abstract String toString();

}