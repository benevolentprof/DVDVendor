/*
 * Creator: Rosalva Gallardo-Valencia
 * Course: Inf111, Winter 2009
 * 
 * Created on January 14, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */
package edu.uci.ics.inf111.dvdvendor.app;

import edu.uci.ics.inf111.dvdvendor.exceptions.InvalidBarCodeException;
import junit.framework.TestCase;
/* 
 * This class contains JUnit test cases for TransactionDB.java.
 * 
 */
public class TransactionDBTest extends TestCase {

	TransactionDB transactionDB;
	
	public TransactionDBTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		transactionDB = new TransactionDB();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		transactionDB = null;
	}


	public void testFindLastRentTransaction() {
		BarCode barCode1 = null;
		BarCode barCode2 = null;
		try {
			barCode1 = new BarCode("717951000842");
			barCode2 = new BarCode("639382000393");
		} catch (InvalidBarCodeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			transactionDB.initializeTestDB();
			RentTransaction rentTransaction = transactionDB.findLastRentTransaction(barCode2);
			System.out.println(rentTransaction.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void testFindLastReturnTransaction() {
		BarCode barCode1 = null;
		BarCode barCode2 = null;
		try {
			barCode1 = new BarCode("717951000842");
			barCode2 = new BarCode("639382000393");
		} catch (InvalidBarCodeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			transactionDB.initializeTestDB();
			ReturnTransaction returnTransaction = transactionDB.findLastReturnTransaction(barCode1);
			System.out.println(returnTransaction.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
