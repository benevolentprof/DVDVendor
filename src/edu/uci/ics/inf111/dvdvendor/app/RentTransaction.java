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

import java.util.ArrayList;
import java.util.Iterator;

import edu.uci.ics.inf111.dvdvendor.exceptions.InvalidBarCodeException;

/**
 * This class represents a Rent Transaction in the point of sale. It
 * contains the main characteristics of a Rent Transaction and it extends
 * the abstract class Transaction
 *
 */
public class RentTransaction extends Transaction{

	/**
	 * The card number for the transaction. 
	 */
	private String cardNumber;
	/**
	 * The ArrayList of bar codes for the transaction. 
	 */
	private ArrayList<BarCode> barCodes;
	/**
	 * Constructor that returns a Rent Transaction with blank values
	 */
	public RentTransaction() {
		this.setDateTime("");
		this.setCardNumber("");
		this.setBarCodes(new ArrayList<BarCode>());		
	}	
	
	/**
	 * Constructor that assigns values based on the information on the TransactionDB
	 * @param line 	String that represents a line in the TransactionDB file
	 * @throws InvalidBarCodeException if the BarCode read is not valid
	 */
	public RentTransaction(String line) throws InvalidBarCodeException {
		String symbol = "|";
		String comma = ",";
		
		//Reading Date and Time
		this.setDateTime(Utils.getFieldBeforeSymbol(line, symbol));
		line = Utils.getLineAfterSymbol(line, "|");
		
		//Reading Cost
		this.setCost(Double.valueOf(Utils.getFieldBeforeSymbol(line, symbol)).doubleValue());
		line = Utils.getLineAfterSymbol(line, "|");
		
		//Reading Card Number
		this.setCardNumber(Utils.getFieldBeforeSymbol(line, symbol));
		line = Utils.getLineAfterSymbol(line, "|");
		
		//Reading Bar Codes
		this.barCodes =  new ArrayList<BarCode>();
		while(!line.equals(""))
		{
			barCodes.add(new BarCode(Utils.getFieldBeforeSymbol(line, comma)));
			line = Utils.getLineAfterSymbol(line, comma);
		}		
	}

	
	/**
	 * An accessor method which sets the ArrayList of BarCodes for the transaction.
	 */
	public void setBarCodes(ArrayList<BarCode> barCodes) {
		this.barCodes = barCodes;
	}
	
	/**
	 * An accessor method which returns the ArrayList of BarCodes for the transaction.
	 */
	public ArrayList<BarCode> getBarCodes() {
		return barCodes;
	}

	/**
	 * Method that adds a BarCode to the ArrayList of BarCodes
	 * @param barCode 	BarCode to be added
	 */
	public void addBarCode(BarCode barCode) {
		this.barCodes.add(barCode);
	}	
	/**
	 * Implementation of the abstract method to define the toString action. 
	 * This method will take the values of the RentTransaction class and
	 * will concatenate them in a String with the expected format in the 
	 * Transaction database.
	 * Format: TypeOfTransaction|DateTime|Cost|CardNumber|BarCode1,BarCode2,BarCode3,
	 * @return String Represents the transaction to be stored in the Transaction
	 * database.
	 */	
	public String toString() {
		String details;
		details = "RENT" + "|" + this.getDateTime() + "|" + this.getCost() + "|" + this.getCardNumber() + "|";
		for(Iterator<BarCode> itr = this.getBarCodes().iterator(); itr.hasNext();){
			details = details + ((BarCode)itr.next()).getBarCode() + ",";
		}
		return details;
	}
	
}
