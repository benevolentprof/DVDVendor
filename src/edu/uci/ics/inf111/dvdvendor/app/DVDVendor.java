/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 8, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 * The DVDVendor class contains functions that can be called by the real user interface
 * of the DVD Vendor system.
 */

package edu.uci.ics.inf111.dvdvendor.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import edu.uci.ics.inf111.dvdvendor.devices.DVDDispenser;
import edu.uci.ics.inf111.dvdvendor.devices.DVDDispenserEvent;
import edu.uci.ics.inf111.dvdvendor.devices.DVDDispenserListener;
import edu.uci.ics.inf111.dvdvendor.devices.PaymentCollector;
import edu.uci.ics.inf111.dvdvendor.exceptions.AddWhileDispensingException;
import edu.uci.ics.inf111.dvdvendor.exceptions.AddWhilePayingException;
import edu.uci.ics.inf111.dvdvendor.exceptions.CalculateWithNoItemException;
import edu.uci.ics.inf111.dvdvendor.exceptions.DispenseWhileAddingException;
import edu.uci.ics.inf111.dvdvendor.exceptions.IncorrectStateException;
import edu.uci.ics.inf111.dvdvendor.exceptions.InvalidProductException;
import edu.uci.ics.inf111.dvdvendor.exceptions.PayWhileDispensingException;
import edu.uci.ics.inf111.dvdvendor.exceptions.PayWithNoItemsException;
import edu.uci.ics.inf111.dvdvendor.exceptions.PaymentCollectorException;
import edu.uci.ics.inf111.dvdvendor.exceptions.ProductAlreadyInCheckOutCartException;
import edu.uci.ics.inf111.dvdvendor.exceptions.ProductAlreadyRentedException;
import edu.uci.ics.inf111.dvdvendor.exceptions.ProductNotRentedException;
import edu.uci.ics.inf111.dvdvendor.exceptions.TransactionDBException;
import edu.uci.ics.inf111.dvdvendor.lib.PersistenceException;


/**
 * The DVDVendor class contains the business logic of the point of sale, and keeps
 * track of the state of the current customer's rental and return checkout.  
 * The class contains methods to handle renting DVDs and returning them.
 *
 */
public class DVDVendor implements DVDDispenserListener {
	/**
	 * This enumeration represents the states of the
	 * DVDVendor system:<br>
	 * <code>READY</code> means the system is awaiting a new customer<br>
	 * 
	 * States for Rental:<br>
	 * <code>ADDING</code> means the system is prepared for another item to be added<br>
	 * <code>PAYING</code> means the system is awaiting for payment<br>
	 * <code>DISPENSING</code> means the system is awaiting notification that the items 
	 * have been dispensed in the DVD Dispenser<br>
	 *
	 * 
	 * States for Returning:<br>
	 * <code>RECEIVING</code> means the system is awaiting for the customer to return the product
	 * using the DVD Dispenser<br>
	 * <code>CALCULATING</code> means the system is calculating the charges.<br>
	 *
	 * Attempts to add items while <code>PAYING</code> or <code>DISPENSING</code> will result in errors.
	 * Attempts to pay for items while <code>DISPENSING</code> will result in errors.
	 * Attempts to dispense items while <code>ADDING</code> will result in errors.
	 * 
	 */
	public enum checkOutState {
		READY, 
		ADDING, PAYING, DISPENSING, 	//Rental
		RECEIVING, CALCULATING			//Return
	}; 
	
	
	/**
	 * The cart containing items the customer has added.
	 */
	private CheckOutCart checkOutCart;

	/**
	 * The associated DVDDispenser, which will notify DVDVendor when it detects a status change.
	 */
	public DVDDispenser dvdDispenser;

	/**
	 * An object representing the credit card or debit card accepting device.
	 */
	private PaymentCollector paymentCollector;

	/**
	 * The database of products in the point of sale.
	 */
	private ProductDB productDB;
	
	/**
	 * The database of transactions in the point of sale.
	 */
	private TransactionDB transactionDB;
	
	/**
	 * The current state of the system.
	 */
	private checkOutState transactionState;
	
	/**
	 * The transaction that will be persisted in the TransactionDB
	 */
	private Transaction transaction;
	
	/**
	 * The argument-less constructor makes the necessary utility classes and passes them to the 
	 * constructor with arguments.
	 * @throws Exception
	 */
	public DVDVendor() throws Exception {
		this(new DVDDispenser(), new PaymentCollector(), ProductDB.getInstance(), new TransactionDB());		
	}
	
	/**
	 * This is the chief constructor.  It records the provided DVDDispenser, PaymentCollector, ProductDB,
	 * and TransactionDB, and attaches itself to the DVDDispenser so that it receives notifications of DVDDispenserEvents.
	 * It also initiate the transaction in state READY 
	 * @param dvdDispenser 	dvdDispenser object for the DVDVendor.
	 * @param payment  		paymentCollector object for the DVDVendor.
	 * @param productDB		productDB object for the DVDVendor.
	 * @param transactionDB transactionDB object for the DVDVendor.
	 * @throws Exception
	 */
	public DVDVendor(DVDDispenser dvdDispenser, PaymentCollector payment,
			ProductDB productDB, TransactionDB transactionDB) throws Exception {
		this.checkOutCart = new CheckOutCart();
		this.dvdDispenser = dvdDispenser;
		this.dvdDispenser.attach(this);
		this.paymentCollector = payment;
		this.productDB = productDB;
		this.transactionDB = transactionDB;
		this.transaction = null;
		transactionState = checkOutState.READY;
	}
	
	/**
	 * This method initiates a rental transaction
	 * 
	 * @throws IncorrectStateException  Thrown when Rental was initiated when the system was not READY
	 */	
	public void initiateRental() throws IncorrectStateException {
		transaction = new RentTransaction();
		if (transactionState.equals(checkOutState.READY))
		{
			transactionState = checkOutState.ADDING;
		}
		else 
		{ 
			throw new IncorrectStateException("Rental was initiated when the system was not READY");
		}
	}
	
	
	/**
	 * This method initiates a return transaction
	 * 
	 * @throws IncorrectStateException  Thrown when Return was initiated when the system was not READY
	 */	
	public void initiateReturn() throws IncorrectStateException {
		transaction = new ReturnTransaction();
		if (transactionState.equals(checkOutState.READY))
		{
			transactionState = checkOutState.RECEIVING;
		}
		else 
		{ 
			throw new IncorrectStateException("Return was initiated when the system was not READY");
		}
	}
	/**
	 * This method accepts a bar code and adds the corresponding 
	 * product to the customer's cart.  First, it checks if the transaction is in the appropriate
	 * state, then it looks for the item in the ProductDB. If the product is valid, then
	 * it will check if the item already exist in the user's cart. 
	 * If the product is new for the cart, then it verifies if the product
	 * is available to be rented. If the item is available, it will
	 * be added to the user's cart.
	 * 
	 * @param barCode	The bar code of the selected item.
	 * @return	Product		The Product which is also added to the CheckOutCart.
	 * @throws InvalidProductException	Thrown when a product corresponding to the bar code 
	 * is not found in the ProductDB
	 * @throws IncorrectStateException Thrown when the user tried to Add an item when the system
	 * was in the state DISPENSING (AddWhileDispensingException) or PAYING (AddWhilePayingException).
	 * @throws TransactionDBException Thrown when there is an exception when looking for the 
	 * last transactions (rent and return) in which the barCode was involved
	 * @throws ParseException Thrown when there is an exception when invoking the method
	 * getSecondsDifference
	 */
	public Product addItem(BarCode barCode) throws InvalidProductException, IncorrectStateException, ParseException, TransactionDBException {

		//If the item is added while dispensing, throw an exception
		if (transactionState == checkOutState.DISPENSING) {
			throw new AddWhileDispensingException("Item added while dispensing.");
			
		//If the item is added while paying, throw an exception
		} else if (transactionState == checkOutState.PAYING) 
		{
			throw new AddWhilePayingException("Item added while payment is being processed.");
		} else {
			
			// Look for the Product in the ProductDB		
			Product product = productDB.lookUpItem(barCode); 
			
			//If the Product was not found, throw an exception
			if (product == null) {
				throw new InvalidProductException("Product was not found in DB.");
			//It checks if the product has not already been added to the CheckOutCart
			} else if (this.checkOutCart.lookUpItem(product) != null) {
				throw new ProductAlreadyInCheckOutCartException("Product is already in CheckOutCart.");
			} else {
				
				//Verify that the product is not rented
				RentTransaction lastRentTransaction = this.getTransactionDB().findLastRentTransaction(barCode);
				if (lastRentTransaction != null) {
					//If it is different from null, it means that it has been rented at least once
					//We need to verify if the DVD was returned after the last rent
					ReturnTransaction lastReturnTransaction = this.getTransactionDB().findLastReturnTransaction(barCode);
					
					if (lastReturnTransaction == null)
					{
						//if it was rented and never returned, then it is still rented
						throw new ProductAlreadyRentedException("This product has already been rented.");
					}
					else if (lastReturnTransaction != null)
					{	
						//Check if the return happened after the rent
						long secondsDifference = Utils.getSecondsDifference(lastRentTransaction.getDateTime(), lastReturnTransaction.getDateTime());
						
						//If the difference is negative, the DVD has not been returned yet
						if (secondsDifference < 0)
						{
							throw new ProductAlreadyRentedException("This product has already been rented.");
							
						}
						
					}
					
				}																	
				// add the product object to vector				
				checkOutCart.addItemToCart(product); 
				return product;
			}
		}
	}

	/**
	 * This method accepts a credit/debit card number to charge for the rented products.
	 * It checks if there are items to pay for in the CheckOutCart, then it checks
	 * that the payment is not done while DISPENSING.
	 * It proceeds with the payment and if everything is ok with it,
	 * it will change the status to PAYING and it will set the information
	 * in the Transaction object. It updates the status to DISPENSING and
	 * returns the CheckOutCart.
	 * @param cardNumber String that represents the credit/debit card Number to be charged.
	 * @return the cart corresponding to the paid transaction.
	 * @throws PaymentCollectorException Thrown if there was an error with the payment. 
	 * It could be that the cardNumber was not valid
	 * @throws IncorrectStateException Thrown if the user tried to pay when he/she did
	 * not select items (PayWithNoItemsException), or when the payment was done while
	 * dispensing (PayWhileDispensingException), or the incorrect type of
	 * transaction was received
	 */
	public CheckOutCart payForRental(String cardNumber) throws PaymentCollectorException, IncorrectStateException {
		DVD dvd;
		Enumeration<Product> itemsInCart;
		
		//It checks if the Payment was done with no items selected
		if (!this.listItemsInCart().hasMoreElements()) {			
			throw new PayWithNoItemsException("Payment done with no items selected.");
		}
		
		//It checks if the Payment was done while Dispensing
		else if (transactionState == checkOutState.DISPENSING){
			throw new PayWhileDispensingException("Payment done while dispensing.");
		}
		else {
						
			// It checks if payment is successful
			if(paymentCollector.collect(cardNumber, checkOutCart.getTotalCost())) {
	
				//It checks if the Transaction is of the correct type
				if (!(transaction instanceof RentTransaction))
					throw new IncorrectStateException("Incorrect type of Transaction. A Rent Transaction type was expected.");
	
				transactionState = checkOutState.PAYING;
				
				//Set current date and time to transaction
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM H:mm:ss");
				transaction.setDateTime(sdf.format(cal.getTime()));
	
				//Set cost to transaction
				((RentTransaction)transaction).setCost(this.getTotalCost());	
				
				//Set car number to transaction
				((RentTransaction)transaction).setCardNumber(cardNumber);	
				
				//Set bar codes to transaction
				itemsInCart = this.listItemsInCart();
		    	while (itemsInCart.hasMoreElements()) {
	    		
		    		dvd = (DVD)itemsInCart.nextElement();
		    		((RentTransaction)transaction).addBarCode(dvd.getBarCode());
		    		
		    	}
							
				// set the state to be ready to dispense the products
				transactionState = checkOutState.DISPENSING;
								
				return checkOutCart;
			}
		}
		return null;
	}

	/**
	 * This method calculate the charges for the Return Transaction.
	 * It checks if a product has been returned using the DVDDispenser, then it checks
	 * if the product is expected to be returned (it has been rented). The
	 * last Rent Transaction is located and the credit/debit card number is 
	 * taken from that transaction. It calculate how much need to be charged to the 
	 * card Number.
	 * It proceeds with the payment and if everything is ok with it,
	 * it will set the information
	 * in the Transaction object. It creates a new empty cart and it updates 
	 * the status to RECEIVING and returns the CheckOutCart.
	 * @param BarCode barCode object that identifies the product to be returned.
	 * @return the cart corresponding to the paid return transaction.
	 * @throws PaymentCollectorException Thrown if there was an error with the payment. 
	 * It could be that the cardNumber was not valid
	 * @throws TransactionDBException Thrown when there is an exception when looking for the 
	 * last transactions (rent and return) in which the barCode was involved
	 * @throws ParseException Thrown when there is an exception when invoking the method
	 * getSecondsDifference
	 * @throws IncorrectStateException Thrown if the user tried to calculateCharges
	 *  when he/she did not select items (CalculateWithNoItemException), or when the 
	 *  product has not been rented (ProductNotRentedException), or the incorrect type of
	 * transaction was received
	 */
	public CheckOutCart calculateChargesForReturn(BarCode barCode) throws PaymentCollectorException, TransactionDBException, ParseException, IncorrectStateException {
		
		//It checks if the CalculateCharges was done with no items selected
		if (transactionState != checkOutState.CALCULATING) 			
			throw new CalculateWithNoItemException("Calculate done without returning a DVD first.");
		
		String cardNumber = "";
		RentTransaction lastRentTransaction = this.getTransactionDB().findLastRentTransaction(barCode);
		
		//It checks if the product was rented before
		if (lastRentTransaction == null)
			throw new ProductNotRentedException("This product has never been rented");
		
		//If rentTransaction exist, check if it has not been returned
		ReturnTransaction lastReturnTransaction = this.getTransactionDB().findLastReturnTransaction(barCode);
		
		if (lastReturnTransaction == null)
		{	//If the transaction does not exist, proceed with Return
			cardNumber = lastRentTransaction.getCardNumber();
			
		}
		else {
			//Check if the return happened after the rent
			long secondsDifference = Utils.getSecondsDifference(lastRentTransaction.getDateTime(), lastReturnTransaction.getDateTime());
			//If the difference is positive or the same the DVD has been returned already
			if (secondsDifference >= 0)
			{
				throw new ProductNotRentedException("This product has not been rented");
				
			}
			else {
				cardNumber = lastRentTransaction.getCardNumber();
			}
		}
		
		//The user can only keep the DVD for 24 hours with no additional cost
		//The user will be charged for each additional day or fraction
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM H:mm:ss");
		String now = sdf.format(cal.getTime());
		Long daysDifference = Utils.getSecondsDifference(lastRentTransaction.getDateTime(), now)/86400;
		int days = daysDifference.intValue();
		checkOutCart.setTotalCost(days*Utils.lateFee);
		
		// check if payment is successful
		if(paymentCollector.collect(cardNumber, checkOutCart.getTotalCost())) {
	
			//Populate values for the return transaction
			if (!(transaction instanceof ReturnTransaction))
				throw new IncorrectStateException("Incorrect type of Transaction. A Return Transaction type was expected.");
				
			//Set current date and time to transaction
			transaction.setDateTime(sdf.format(cal.getTime()));		
			
			//Set card number to transaction
			transaction.setCardNumber(cardNumber);
			
			//Set barCode to transaction
			((ReturnTransaction)transaction).setBarCode(barCode);
			
			//Set cost to transaction
			((ReturnTransaction)transaction).setCost(this.getTotalCost());	
						
			// make a copy of the old cart
			CheckOutCart cc = checkOutCart;
			
			// replace the full cart with a new one
			checkOutCart = new CheckOutCart();
			
			// reset our state to waiting for a customer.
			transactionState = checkOutState.RECEIVING;
			
						
			// return the old cart for examination
			return cc;
		}
		return null;
	}

	/**
	 * Method that dispense the items in the transaction using the dvdDispenser object 
	 * from the dvdVendor object. First, it will check the correct status to dispense,
	 * then it will dispense all the products in the cart, and finally it will
	 * record the transaction in the Transaction file
	 * @return String that contains the transaction recorded in the format
	 * used in the Transaction file
	 * @throws IncorrectStateException Thrown if the dispense was done while adding
	 * @throws PersistenceException Thrown if there was an error in persisting
	 * the transaction in the Transaction file
	 */ 
	public String dispenseItems() throws IncorrectStateException, PersistenceException
	{
		// It checks the correct status to dispense
		if (transactionState == checkOutState.ADDING) {
			throw new DispenseWhileAddingException("Dispense done while adding.");
		}
		else {
			//It dispenses all the products in the cart
			Enumeration<Product> dispenseListItemsInCart = this.listItemsInCart();		
			Product product;
			while (dispenseListItemsInCart.hasMoreElements()) {
				product = dispenseListItemsInCart.nextElement();
				this.dvdDispenser.dispense(product.getBarCode().getBarCode());
			}
			//It records the transaction and return the String recorded
			return recordTransaction();
		}
	}
	
	/**
	 * Method that receives the returned item using the dvdDispenser object from the 
	 * dvdVendor object
	 * @param barCode String with the bar code of the returned item
	 * @throws Exception Thrown if there are problem with the DVDDispenser
	 */ 
	public void receiveItem(String barCode) throws Exception
	{
		this.dvdDispenser.receive(barCode);
	
	}	
	
	/**
	 * Method that records the transaction in the Transaction file
	 * @return String that contains the transaction recorded in the format
	 * used in the Transaction file
	 * @throws PersistenceException Thrown if there was an error in persisting
	 * the transaction in the Transaction file
	 */ 	
	public String recordTransaction() throws PersistenceException
	{
		return this.transactionDB.persistTransaction(this.transaction);
		
	}
	
	/**
	 * This method retrieves an enumeration of all the items currently in the cart
	 * and returns it.
	 */
	public Enumeration<Product> listItemsInCart() {
		return  checkOutCart.listItems();
	}

	/**
	 * This method returns the current total cost of all items in the cart.
	 */
	public double getTotalCost() {
		return checkOutCart.getTotalCost();
	}

	/**
	 * When the DVDDispenser detects a change in its state, this function
	 * is called to change the state of the system (transactionState).
	 * If a RECEIVE event is trigger, then the transactionState is updated
	 * to CALCULATING.
	 * If a DISPENSE event is trigger, then the checkOutCart is restarted
	 * and the transactionState is updated to ADDING.
	 * @param dd	The attached DVDDispenser which is sending the event.
	 * @param event	The DVDDispenserEvent, which includes the type of event 
	 * and the read bar code. 
	 */
	public void notifyDVDDispenserEvent(DVDDispenser dd, DVDDispenserEvent event) {
		if (event.getEventType().equals("RECEIVE"))
		{
			transactionState = checkOutState.CALCULATING;
		}
		else if (event.getEventType().equals("DISPENSE"))
		{ //TODO: It should check if all the DVDs has been dispensed and not only one
			
			// replace the full cart with a new one
			checkOutCart = new CheckOutCart();			
			
			transactionState = checkOutState.ADDING;
		}
	}
	
	/**
	 * An accessor method which returns the DVDDispenser associated with this DVDVendor.
	 * Useful if the application wants to also receive DVD Dispenser events, for example.
	 */
	public DVDDispenser getDVDDispenser() {
		return this.dvdDispenser;
	}
	
	/**
	 * An accessor method which returns the ProductDB associated with this DVDVendor.
	 * Useful if the application wants to add items to the database or to look up items.
	 */
	public ProductDB getProductDB() {
		return this.productDB;		
	}
	/**
	 * An accessor method which returns the TransactionDB associated with this DVDVendor.
	 * Useful if the application wants to add items to the database or to look up items.
	 */
	public TransactionDB getTransactionDB() {
		return this.transactionDB;		
	}
	
	/**
	 * An accessor method which returns the PaymentCollector associated with this DVDVendor.
	 * Useful if the application wants to get payment details such as credit card number.
	 */
	public PaymentCollector getPaymentCollector() {
		return paymentCollector;
	}
	/**
	 * An accessor method which returns the Transaction State
	 * Useful if the application wants to get the current state of the transaction.
	 */
	public checkOutState getTransactionState() {
		return transactionState;
	}
}
