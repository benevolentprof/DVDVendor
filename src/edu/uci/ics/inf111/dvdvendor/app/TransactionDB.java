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

import edu.uci.ics.inf111.dvdvendor.exceptions.TransactionDBException;
import edu.uci.ics.inf111.dvdvendor.lib.Persistence;
import edu.uci.ics.inf111.dvdvendor.lib.PersistenceException;

/**
 * The TransactionDB class encapsulates the list of all products sold in the store. 
 * In a real system, this would likely be a wrapper around a database of products
 * which would be managed elsewhere.  In our sample system, we have a method which 
 * can provide a sample DB, and the capability to add items to the DB using an
 * addItem() method. The TransactionDB is persisted in a file.
 *
 */
public class TransactionDB {
	
	/**
	 * This ArrayList of Transactions is the core of our sample DB.  In a real 
	 * implementation, the actual data would likely be in a separate database, 
	 * which we would access using database queries. In this case, the transactions 
	 * are read from a txt file and stored in the same txt file. Each line in the file
	 * is represented as one entry in this ArrayList.
	 */
	private ArrayList<Transaction> transactions;
	
	/**
	 * Persistence object that will help persist the database information in a file
	 */
	private Persistence persistence;

	/**
	 * Constructs an empty database.
	 */
	public TransactionDB() {
		transactions = new ArrayList<Transaction>();
	}

	/**
	 * This test method constructs the transaction database from a txt file that 
	 * contains all the transactions. Each Transaction will be read from the file
	 * and added to this class using the addItem() method.
	 * @throws Exception
	 */
	public void initializeTestDB() throws Exception {
		/* 
		 * This try block is used to capture the various code- and item-creation exceptions
		 * which are thrown by BarCode()
		 */ 
		try {
			//Indicate the location of the file
			persistence = new Persistence("db/TransactionsDB.txt");
			
			//Read the transactions in the file
			ArrayList<String> linesRead = persistence.read();
			
			//Add each line read in the Transaction file
			Iterator<String> iterator = linesRead.iterator();
			String tmpLine;
			while (iterator.hasNext())
			{
				tmpLine = (String)iterator.next();
				addItem(Transaction.createTransaction(tmpLine));
				
			}
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * This method returns a copy of the TransactionDB ArrayList.  If we
	 * provided the original, external code could modify the DB directly.
	 */
	public ArrayList<Transaction> listAll() {
		// make a copy of transactions ArrayList before returning
		ArrayList<Transaction> copyAL = new ArrayList<Transaction>(transactions);
		return copyAL;
	}
	
	/**
	 * This method finds the last Rent Transaction in the database for a specific BarCode.  
	 * @param barCode	The bar code of the product involved in the Rent Transaction
	 * @return	RentTransacion The class of the corresponding transaction, or null if no such transaction.
	 * @throws TransactionDBException 
	 */
	public RentTransaction findLastRentTransaction(BarCode barCode) throws TransactionDBException {
		int index = transactions.size()-1;
		boolean found = false;
		Transaction retrievedTransaction;
		RentTransaction foundRentTransaction = null;
		RentTransaction tempRentTransaction;
		ArrayList<BarCode> tempBarCodeList;
		BarCode tempBarCode;
		
		//It will search for the BarCode starting on the last transaction
		while((!found) && (index >= 0))
		{
			retrievedTransaction = transactions.get(index);
			
			//It will only examine the RentTransactions
			if (retrievedTransaction instanceof RentTransaction) 
			{
				tempRentTransaction = (RentTransaction) retrievedTransaction;
				tempBarCodeList = tempRentTransaction.getBarCodes();
				Iterator<BarCode> iterator = tempBarCodeList.iterator();
				
				//It checks in all the BarCodes involved in the Transaction
				while ((!found) && (iterator.hasNext()))
				{
					tempBarCode = (BarCode)iterator.next();
					if (tempBarCode.getBarCode().equals(barCode.getBarCode()))
					{
						found = true;
						foundRentTransaction = tempRentTransaction;
					}
				}
			}
			index--;
							
		}
		return foundRentTransaction;
	}
	
	/**
	 * This method finds the last Return Transaction in the database for a specific BarCode.  
	 * @param barCode	The bar code of the product involved in the Return Transaction
	 * @return	ReturnTransacion The class of the corresponding transaction, or null if no such transaction.
	 * @throws TransactionDBException 
	 */	
	public ReturnTransaction findLastReturnTransaction(BarCode barCode) throws TransactionDBException {
		int index = transactions.size()-1;
		boolean found = false;
		Transaction retrievedTransaction;
		ReturnTransaction foundReturnTransaction = null;
		ReturnTransaction tempReturnTransaction;
		BarCode tempBarCode;
		
		//It will search for the BarCode starting on the last transaction
		while((!found) && (index >= 0))
		{
			retrievedTransaction = transactions.get(index);
			
			//It will only examine the ReturnTransactions
			if (retrievedTransaction instanceof ReturnTransaction) 
			{
				tempReturnTransaction = (ReturnTransaction) retrievedTransaction;
				tempBarCode = tempReturnTransaction.getBarCode();
				
				//It checks if the one we are looking for
				if (tempBarCode.getBarCode().equals(barCode.getBarCode()))
				{
					found = true;
					foundReturnTransaction = tempReturnTransaction;
				}

			}
			index--;
							
		}
		
		return foundReturnTransaction;
	}
	
	/**
	 * This method is called to add items directly to the database in our example.  In a 
	 * real implementation, this would likely be done directly to the product database
	 * using a separate piece of software.
	 * @param transaction	The transaction to be added.
	 */
	public void addItem(Transaction transaction) {
		transactions.add(transaction);

	}
	
	/**
	 * This method is called to persist a Transaction in the Transaction file. 
	 * @param transaction	The transaction to be persisted in the file.
	 * @return line 		The String representation of the Transaction using the 
	 * format in the Transaction file
	 * @throws PersistenceException If the transaction passed as a parameter is null
	 * or if there is an error while writing the file.
	 */	
	public String persistTransaction(Transaction transaction) throws PersistenceException
	{
		if (transaction == null)
			throw new PersistenceException("Transaction object is null");
		String line = transaction.toString();
		persistence.write(line);		
		return line;
			
	}
}