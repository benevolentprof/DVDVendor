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


import junit.framework.TestCase;

/* 
 * This class contains JUnit test cases for Utils.java.
 * 
 */

public class UtilsTest extends TestCase {

	public void testPrintDB() throws Exception {

		ProductDB productDB = ProductDB.getInstance();
		Utils.printDB(productDB.listAll());
		
	}
		
	public void testGetBarCodesAndTitles() throws Exception {
		

		String[] labels = Utils.getBarCodesAndTitlesFromProductDB();
		System.out.println("Labels :" + labels);
		 
	}
	
	public void testGetBarCodeFromDescription() throws Exception {
		String description = "024543213710 - Mamma Mia!";
		String barCode = Utils.getBarCodeFromDescription(description);
		System.out.println("Description: " + description);
		System.out.println("BarCode: " +  barCode);
		
	}

	public void testGetFieldBeforeSymbol() throws Exception {
		String line = "2009/19/01 5:54:19|2.0|1234567890123456|071009003507,717951000842,";
		String  field= Utils.getFieldBeforeSymbol(line, "|");
		System.out.println("Line: " + line);
		System.out.println("Field: " +  field);
		
	}
	
	public void testGetLineAfterSymbol() throws Exception {
		String line = "2009/19/01 5:54:19|2.0|1234567890123456|071009003507,717951000842,";
		System.out.println("Line: " + line);
		line= Utils.getLineAfterSymbol(line, "|");
		System.out.println("Line After: " +  line);
		
	}
	

	public void testSecondsDifference() throws Exception {
		String start = "2009/16/11 1:11:03";
		String end = "2009/16/11 1:11:05";
		System.out.println("Difference in Seconds: " + Utils.getSecondsDifference(start, end));
		
		
	}
}
