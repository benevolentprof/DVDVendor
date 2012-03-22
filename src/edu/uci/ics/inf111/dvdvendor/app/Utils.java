/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 14, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */
package edu.uci.ics.inf111.dvdvendor.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class contains constants, enums, and static methods that are commonly
 * used in the DVD Vendor System.
 *
 */
public class Utils {

	/**
	 * The double representation for the late fee to be charged per DVD. 
	 */
	public static double lateFee = 1.0;
	
	/**
	 * Enumeration that represents the different genres the DVD can belong to. 
	 */
	public enum genre {
		ACTION_ADVENTURE, ANIMATION, COMEDY, CRIME, DRAMA,
		FAMILY, FOREIGN, HITMOVIES, HOLIDAY, HORROR, KIDS, 
		MUSICAL, ROMANCE, SCIFI_FANTASY, SUSPENSE,
		TELEVISION, WAR, WESTERN
	}; // different movie genres
	
	/**
	 * A utility method which prints the contents of the ProductDB to System.out
	 * @param itemsInDB  The DB Hashtable, which should be accessed using productDB.listAll()
	 */
	public static void printDB(Hashtable<String, Product> itemsInDB) {
		Enumeration<Product> items = itemsInDB.elements();
		Product retrievedItem;
		DVD tempDVD;
		
		System.out.println("There are " + itemsInDB.size()
				+ " items in the database.");
		System.out
				.println("*** Below is a list of products currently in the database.***");
		System.out
				.println("-------------------------------------------------------");

		while (items.hasMoreElements()) {
			retrievedItem = items.nextElement();

			if (retrievedItem instanceof DVD) 
			{
				tempDVD = (DVD) retrievedItem;
				System.out.println("Product BarCode: "
						+ tempDVD.getBarCode().getBarCode());
				System.out.println("Title: " + tempDVD.getTitle());
				System.out.println("Price: " + tempDVD.getPrice());
				System.out.println("Plot: " + tempDVD.getPlot());
				System.out.println("Genre: ");
				for (Utils.genre tempgenre : tempDVD.getGenre()) 
				{ System.out.println(tempgenre.toString() + " "); }
				System.out.println("Running Time: " + tempDVD.getRunningTime());
				System.out.println("Starring: " + tempDVD.getStarring());

				
			}  else {
				// This should never execute
				System.out.println("ERROR: Product type unknown.");
			}
		}

		System.out
				.println("-------------------------------------------------------");
	}

	/**
	 * This utility method returns an array of String with the Bar Codes and Titles
	 * from the Product Database
	 * @throws Exception if there is any problem instantiating the ProductDB 
	 */
	public static String[] getBarCodesAndTitlesFromProductDB() throws Exception {

		ProductDB productDB; 
		
		// Obtains the instance for ProductDB. If this is the first time the 
		// ProductDB is instantiated, then it could throw an Exception due
		// to problems creating the DB, for example, it could be that
		// a product is repeated in the DB
		productDB = ProductDB.getInstance();
		Enumeration<Product> items = productDB.listAll().elements();
		Product retrievedItem;
		DVD tempDVD;
		
		//Create the Array of Strings based on the size of Products in the DB
		String [] labels = new String[productDB.listAll().size()];
		int index = 0;
		while (items.hasMoreElements()) {
			retrievedItem = items.nextElement();

			if (retrievedItem instanceof DVD) {
				tempDVD = (DVD) retrievedItem;
				labels[index++] = 	tempDVD.getBarCode().getBarCode() + " - " + 
				tempDVD.getTitle();	
								
			}
		}
		return labels;		
	}		
	
	/**
	 * This utility method returns true if the characters in the String are all numbers,
	 * else it returns false 
	 */
	public static boolean isNumber(String str) {
	
		for (int i = 0; i < str.length(); i++) {
	
	        //If we find a non-digit character we return false.
	        if (!Character.isDigit(str.charAt(i)))
	            return false;
	    }
   
		return true; 
	}

	/**
	 * This utility method returns the String that represents the BarCode from a 
	 * String that has both the BarCode and the Title of the DVD. This 
	 * method is used to extract the bar code from the ComboBox in the
	 * Graphical User Interface
	 * @param description String showed in the ComboBox in the format barcode - title
	 */
	public static String getBarCodeFromDescription(String description) {
		String barCode = "";
		int indexDash = description.indexOf("-");
		barCode = description.substring(0, indexDash-1);  
		return barCode; 
	}
	
	/**
	 * This utility method returns the String that represents the field between
	 * the beginning of the line and the symbol. It is used to get the 
	 * values of a Transaction read from the Transaction file
	 * @param line String that represents a line in the Transaction file
	 * @param symbol Stop symbol used to delimit fields in the Transaction file
	 */	
	public static String getFieldBeforeSymbol(String line, String symbol) {
		String field = "";
		int indexSymbol = line.indexOf(symbol);
		field = line.substring(0, indexSymbol);
   
		return field; 
	}

	/**
	 * This utility method returns the String that represents the line after
	 * the indicated symbol. It will delete a read field, so that the next one
	 * could be read. These fields correspond to fields in the Transaction file.
	 * @param line String that represents a line in the Transaction file
	 * @param symbol Stop symbol used to delimit fields in the Transaction file
	 */	
	public static String getLineAfterSymbol(String line, String symbol) {
		int indexSymbol = line.indexOf(symbol);
		return line.substring(indexSymbol+1);
   
	}
	
	/**
	 * A utility method which calculates the difference in seconds between two date times
	 * @param start The start date and time
	 * @param end  	The end date and time
	 * @throws ParseException
	 */
	public static long getSecondsDifference(String start, String end) throws ParseException {
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM H:mm:ss");
		Date s = sdf.parse(start);
		Date e = sdf.parse(end);
		
		//Set the start time		            
		Calendar sCal = java.util.GregorianCalendar.getInstance();
		sCal.setTime(s);
		
		//Set the end time
		Calendar eCal =java.util.GregorianCalendar.getInstance();
		eCal.setTime(e);
		                  
		//It return seconds
		long result = (e.getTime() - s.getTime())/1000;
		return result;
	}
}
