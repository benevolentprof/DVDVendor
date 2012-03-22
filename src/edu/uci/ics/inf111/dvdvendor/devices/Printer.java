/*
 * Creator: Rosalva Gallardo-Valencia
 * Course: Inf111, Winter 2008
 * 
 * Created on Feb 14, 2008
 * Updated on Feb 18, 2008
 * 
 * Copyright, 2008 University of California. 
 * 
 * The Printer class handles the printing process. This class has two constructors.
 * The constructor without arguments will create a printer whose output will be shown 
 * in the console screen. The constructor with one argument will create a printer
 * whose output will be shown in a file.
 * To print a receipt on the screen or file, you should call the method print and pass 
 * an array of Strings as parameter. The print function will check if each String 
 * does not exceed the maximum of characters per line, defined by lineLenght. If the 
 * String exceeds, the method will only print the first lineLength characters.
 */

package edu.uci.ics.inf111.dvdvendor.devices;


import java.util.ArrayList;
import java.util.Iterator;

import edu.uci.ics.inf111.dvdvendor.exceptions.PrinterException;
import edu.uci.ics.inf111.dvdvendor.lib.Persistence;
import edu.uci.ics.inf111.dvdvendor.lib.PersistenceException;


/**
 * This class represents the printer of the system. The output of this printer
 * could be the console or a file.
 *
 */
public class Printer {
	/**
	 * Persistence object that will help to persist the information in the
	 * Printer.
	 */
	private Persistence persistence;
	/**
	 * Contains the maximum length of the printer.
	 */
	int lineLength = 38;
	/**
	 * Contains the path of the output file for the printer.
	 */
	String targetFile="";
	/**
	 * This constructor creates a printer whose output will be shown 
	 * in the console screen. It also initializes the variables targetFile.
	 */
	public Printer() {
		targetFile = "";
	}
	/**
	 * This constructor creates a printer whose output will be shown in a file.
	 * @param pTargetFile	A String containing the path of the output file. It is recommended
	 * to use a relative path (ie "ptr/PrinterOutput.txt") due to the fact that this will
	 * work with both PC and MAC. If you create it using a relative path,
	 * the file will be stored in the location of your project (Right click on the project,
	 * Properties, see Location). 
	 * @throws PrinterException
	 * @throws PersistenceException 
	 */
	public Printer(String pTargetFile) throws PrinterException, PersistenceException {
		persistence = new Persistence(pTargetFile);
	}

	/**
	 * Prints the arrayList of Strings passed as parameter on the screen or file, 
	 * depending on the constructor used to create the printer object.
	 * The print function will check that the arrayList is not null and that 
	 * the elements in the arrayList are not null.
	 * Also it will check if each String in the arrayList
	 * does not exceed the maximum of characters per line, defined by lineLenght. If the 
	 * String exceeds, the method will only print the first lineLength characters.
	 * While printing, this method will add a new line character at the end of each String in the arrayList.
	 * In the case there would be any problem printing the data to the file using the 
	 * Persistence object a Exception is thrown. When the Persistence object is used
	 * the maximum of characters per line is not checked.
	 * @param dataToPrint ArrayList of Strings that contains the list of Strings that
	 * will be printed
	 * @throws PrinterException
	 * @throws PersistenceException 
	 */
	public void print(ArrayList<String> dataToPrint) throws PrinterException, PersistenceException
	{
		String line;
		// Check if the ArrayList is not null
		if (dataToPrint == null)
		{
			throw (new PrinterException("Data sent to print is null."));
		}
		
		Iterator<String> iterator = dataToPrint.iterator();
		
		//Print the output in the Console Screen
		if (targetFile.equals(""))
		{
			//Read each element in the array			
			while (iterator.hasNext())
			{
				line = (String)iterator.next();
				
				// Check if the current element in the ArrayList is not null
				if (line == null)
				{
					throw (new PrinterException("One line sent to print is null."));
				}
				//If the String exceeds the limit of characters 
				if (checkLength(line) == false) 
				{	//Print the first lineLenght characters and a new line
					System.out.print(line.substring(0, lineLength) + "\n");
				}
				else
				{	//Print the String and a new line
					System.out.print(line + "\n");
				}
			}
			
		}
		else
		{
			persistence.write(dataToPrint);
	
		}
		
	}
	/**
	 * Return the value of lineLength
	 * @return lineLength
	 */
	public int getLineLength()
	{
		return lineLength;
	}

	/**
	 * Checks that the length of a String does not exceed lineLength.
	 * @param line
	 * @return <code>true</code> if the string length is greater than lineLength; <code>false</code> otherwise.
	 */
	private boolean checkLength(String line) {
		if (line.length() <= lineLength) {
			return true;
		} else {
			return false;
		}
	}	
}
