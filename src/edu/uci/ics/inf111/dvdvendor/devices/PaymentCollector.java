/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 8, 2009
 * 
 * Copyright, 2009 University of California. 
 *
 * 
 * The PaymentCollector class handles the payment process. This is not a concern for this assignment.
 * It is created to make the system more "complete," but it lacks real functionalities.
 */

package edu.uci.ics.inf111.dvdvendor.devices;

import edu.uci.ics.inf111.dvdvendor.app.Utils;
import edu.uci.ics.inf111.dvdvendor.exceptions.PaymentCollectorException;

/**
 * This class represents the payment-collecting portion of the system: the 
 * credit-card or debit-card reader of the system.  We are not concerned with 
 * this part of the system, so a simple stub method with a simple validation
 * for the credit/debit card number is provided.
 *
 */
public class PaymentCollector {
	
	/**
	 * The String representation of the 16-digit card number. 
	 */
	private String cardNumber;
	
	/**
	 * Constructor for PaymentCollector 
	 */
	public PaymentCollector() {
		this.cardNumber = "";
	}

	/**
	 * This method is called by DVDVendor when the customer is finished adding items and
	 * wishes to pay.  It is a stub method which returns <code>true</code>, indicating successful payment.
	 * It can throw a PaymentCollectorException when the card number 
	 * passed as parameter does not have a length of 16 characters
	 * or the card number contains characters that are not numbers
	 * @param cardNumber		The 16-digit card number.
	 * @param amount			The amount of payment requested.
	 * @return <code>true</code> indicating payment accepted.
	 * @throws PaymentCollectorException if the cardNumber does not have a length of 16 characters
	 * or the card number contains characters that are not numbers
	 */
	public boolean collect(String cardNumber, double amount) throws PaymentCollectorException {
		// For this project, we are not concerned with this part of the system.
		// We will just assume
		// that whenever this function is called, this function will 
		// return true to the
		// calling program to indicate that the payment has been accepted, except
		// in the case that the cardNumber does not have a length of 16 characters
		// or the card number contains characters that are not numbers

		//Check if the cardNumber has 16 characters
		if ((cardNumber.length() != 16) )
		{
			throw (new PaymentCollectorException("Card Number does not have 16 characteres"));

		//Check if the cardNumber's characters are numbers
		} else if (!Utils.isNumber(cardNumber))
		{		
				throw (new PaymentCollectorException("Invalid Card Number"));			
		}
		//If not exception is thrown, it will return true
		return true;
	}	
}
