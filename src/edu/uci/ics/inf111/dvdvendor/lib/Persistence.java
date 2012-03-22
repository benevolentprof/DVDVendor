/*
 * Creator: Rosalva Gallardo-Valencia
 * Course: Inf111, Winter 2009
 * 
 * Created on January 14, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */
package edu.uci.ics.inf111.dvdvendor.lib;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * The Persistence class represents the interface to physically store 
 * information in a persistent way. In this case, the persistence is done 
 * in a file in the file system.
 * 
 *
 */


public class Persistence {

	/**
	 * Contains the path of the output file.
	 */
	String targetFile;
	/**
	 * This constructor creates a persistance whose output will be shown in a file.
	 * This method also checks that the targetFile path exists. In the case that 
	 * targetFile path does not exist, a PersistenceException is thrown.
	 * @param pTargetFile	A String containing the path of the output file. It is recommended
	 * to use a relative path (ie "prt/PrinterOutput.txt") due to the fact that this will
	 * work with both PC and MAC. If you create it using a relative path,
	 * the file will be stored in the location of your project (Right click on the project,
	 * Properties, see Location). 
	 * @throws PersistenceException 
	 */
	public Persistence(String pTargetFile) throws PersistenceException {
		targetFile = pTargetFile;
		try { 
			/* 
			 * It checks if the targetPath exists.
			 */				
			File file = new File(targetFile);
			FileOutputStream fos = new FileOutputStream(file, true);
		    fos.close();
		    } 
		catch (FileNotFoundException e) {
			/* 
			 * We throw an exception if the targetPath does not exist.
			 * The message provides details about the type of exception that occurred.
			 */			
		    //e.printStackTrace();
			throw (new PersistenceException("File not found. Please check the path you indicated."));

		    }
		catch (IOException e) {
			/* 
			 * We throw an exception if there was an io exception.
			 * The message provides details about the type of exception that occurred.
			 */			
		    //e.printStackTrace();
			throw (new PersistenceException("IOException. Please check the path you indicated."));

		    }
	}
	
	/**
	 * Writes in the targetFile the arrayList of Strings passed as parameter.
	 * The write function will check that the arrayList is not null and that 
	 * the elements in the arrayList are not null.
	 * While writing, this method will add a new line character at the end of each String in the arrayList.
	 * In the case there would be any problem writing the data to the file, 
	 * a PersistenceException is thrown.
	 * @param dataToPrint ArrayList of Strings that contains the list of Strings that
	 * will be stored in the targetFile
	 * @throws PersistenceException
	 */
	public void write(ArrayList<String> dataToPrint) throws PersistenceException
	{
		String line;
		// Check if the ArrayList is not null
		if (dataToPrint == null)
		{
			throw (new PersistenceException("Data sent to print is null."));
		}
		
		Iterator<String> iterator = dataToPrint.iterator();
		

		FileOutputStream fos; 
		DataOutputStream dos;

		try {
			  //Create the file and output streams for the output
		      File file = new File(targetFile);
		      fos = new FileOutputStream(file, true);
		      dos=new DataOutputStream(fos);

		    //Read each element in the array	
		      while (iterator.hasNext())
		      {
		    	  line = (String)iterator.next();
		    	 // Check if the current element in the ArrayList is not null
		    	  if (line == null)
					{
						throw (new PersistenceException("One line sent to print is null."));
					}
		    	  //Print the String and a new line
		    	  dos.writeBytes(line + "\n");
		    	  
			  }
		      //Close the output streams
		      dos.close();
		      fos.close();

		  } catch (IOException e) {
		    //e.printStackTrace();
	    	throw (new PersistenceException("Persistance Exception occurred while writing file."));

		  }
				
	}
	
	/**
	 * Writes in the targetFile the String passed as parameter.
	 * This method calls internally the write method that
	 * receives a ArrayList<String> as a parameter.
	 * @param dataToPrint String that contains the info to be stored in the targetFile
	 * @throws PersistenceException
	 */	
	public void write(String dataToPrint) throws PersistenceException
	{
		 ArrayList<String> lines = new ArrayList<String>();
		 lines.add(dataToPrint); 
		 write(lines);		
	}
	
	/**
	 * Reads the targetFile line by line and keep all the lines 
	 * read in an ArrayList of Strings.
	 * @throws PersistenceException
	 */		
	public ArrayList<String> read() throws PersistenceException
	{
		
		ArrayList<String> lines = new ArrayList<String>();

		try {
			  //Create the reader
		      File file = new File(targetFile);
		      BufferedReader input =  new BufferedReader(new FileReader(file));
		      
		      String line = null;	
		      while (( line = input.readLine()) != null){
		          lines.add(line);
		        }
		      return lines;

		  } catch (IOException e) {
		    //e.printStackTrace();
	    	throw (new PersistenceException("Persistance Exception occurred while reading file."));

		  }				
	}
}
