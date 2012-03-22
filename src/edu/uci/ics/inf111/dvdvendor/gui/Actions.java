/*
 * Creator: Rosalva Gallardo-Valencia
 * Course: Inf111, Winter 2009
 * 
 * Created on January 14, 2009
 * 
 * Copyright, 2009 University of California. 
 *
 */
package edu.uci.ics.inf111.dvdvendor.gui;

import java.util.Enumeration;

import edu.uci.ics.inf111.dvdvendor.app.BarCode;
import edu.uci.ics.inf111.dvdvendor.app.DVD;
import edu.uci.ics.inf111.dvdvendor.app.DVDVendor;
import edu.uci.ics.inf111.dvdvendor.app.Product;
import edu.uci.ics.inf111.dvdvendor.app.ProductDB;
import edu.uci.ics.inf111.dvdvendor.app.TransactionDB;
import edu.uci.ics.inf111.dvdvendor.devices.DVDDispenser;
import edu.uci.ics.inf111.dvdvendor.devices.PaymentCollector;


/**
 * This class contains the actions that can be called from the Graphical User
 * Interface of the DVD Vendor System. It also includes an internal
 * action to print the contents of the shopping cart
 * 
 */
public class Actions {
/**
 * Method that creates a new DVD Vendor object that includes 
 * dvdDispenser, paymentCollector, productDB, and transactionDB objects
 * @return	new dvdVendor object
 * @throws Exception
 */ 	
	protected DVDVendor start() throws Exception
	{
		DVDVendor dvdVendor;
		DVDDispenser dvdDispenser;
		ProductDB productDB;
		TransactionDB transactionDB;
		PaymentCollector paymentCollector;
		
		dvdDispenser = new DVDDispenser();
		paymentCollector = new PaymentCollector();
		productDB = ProductDB.getInstance();
		transactionDB = new TransactionDB();
		transactionDB.initializeTestDB();
		dvdVendor = new DVDVendor(dvdDispenser, paymentCollector, productDB, transactionDB);
		return dvdVendor;
	}
	/**
	 * Method that initiates the Rent Transaction
	 * @throws Exception
	 */	
	protected DVDVendor initiateRent() throws Exception
	{
		DVDVendor dvdVendor = start();
		dvdVendor.initiateRental();
		return dvdVendor;
	}
	/**
	 * Method that initiates the Return Transaction
	 * @throws Exception
	 */	
	protected DVDVendor initiateReturn() throws Exception
	{
		DVDVendor dvdVendor = start();
		dvdVendor.initiateReturn();
		return dvdVendor;
	}
	
	/**
	 * Method that creates a new BarCode object with the barCodeNumber
	 * given as parameter, then it adds the BarCode to the DVDVendor object.
	 * It returns the product that was added to the shopping cart
	 * @param dvdVendor DVDVendor object where the product will 
	 * be added
	 * @param barCodeNumber String that contains the bar code
	 * @return	product that was added to the shopping cart
	 * @throws Exception
	 */ 	
	protected Product addDVD(DVDVendor dvdVendor, String barCodeNumber) throws Exception
	{
		BarCode barCode = new BarCode(barCodeNumber);
		Product product = dvdVendor.addItem(barCode);
		return product;	
	}

	/**
	 * Method that dispense the items in the transaction using the dvdDispenser object from the dvdVendor object
	 * @param dvdVendor DVDVendor object for the transaction
	 * @throws Exception
	 */ 
	protected String dispenseItems(DVDVendor dvdVendor) throws Exception
	{
		return dvdVendor.dispenseItems();
	}
	
	/**
	 * Method that receives the returned item using the dvdDispenser object from the dvdVendor object
	 * @param dvdVendor DVDVendor object for the transaction
	 * @param barCode String with the bar code of the returned item
	 * @throws Exception
	 */ 
	protected void receiveItem(DVDVendor dvdVendor, String barCode) throws Exception
	{
		dvdVendor.receiveItem(barCode);
	
	}
	/**
	 * Method that collects the payment for all the products in the cart
	 * @param dvdVendor DVDVendor object for the transaction
	 * @param cardNumber String with the credit/debit card Number that will be used to
	 * charge the cost of the transaction
	 * @throws Exception
	 */ 	
	protected void payItems(DVDVendor dvdVendor, String cardNumber) throws Exception
	{
		dvdVendor.payForRental(cardNumber);
	}
	
	/**
	 * Method that calculates the charges for the returned product
	 * @param dvdVendor DVDVendor object for the transaction
	 * @param barCode String with the bar code of the returned item
	 * @throws Exception
	 */ 	
	protected String calculateCharges(DVDVendor dvdVendor, String barCode) throws Exception
	{
		dvdVendor.calculateChargesForReturn(new BarCode(barCode));
		return dvdVendor.recordTransaction();
	}
	/**
	 * Method that prints the contents of the shopping cart
	 * This method is called internally for the GUI class and does not
	 * correspond to any external GUI action
	 * @param listItemsInCart Enumeration of Products that are in the
	 * shopping cart
	 * @throws Exception
	 */ 	
    protected String printShoppingCart(Enumeration<Product> listItemsInCart) throws Exception
    {
    	String returnString = "\n";
    	int count = 0;
    	DVD dvd;
    	String line;
    	while (listItemsInCart.hasMoreElements()) {
    		count++;
    		dvd = (DVD)listItemsInCart.nextElement();
    		
    		line = "Item " + count + ": ";
    		line += dvd.getBarCode().getBarCode();
    		line += "-";
    		line += dvd.getTitle();
    		line += " ";
    		line += dvd.getPrice();
    		line += "";
    		line += dvd.getRunningTime();
    		line += "\n";
    		returnString += line;
    		    		
    	}
    	return returnString;
    }
}
