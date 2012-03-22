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

import java.util.ArrayList;
import java.util.Iterator;
import junit.framework.TestCase;

/* 
 * This class contains JUnit test cases for Persistence.java.
 * 
 */
public class PersistenceTest extends TestCase {

	Persistence persistance;
	ArrayList<String> lines;
	ArrayList<String> linesRead;
	String line;
	
	public PersistenceTest(String name) {
		super(name);
		
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		persistance = new Persistence("db/TestTransactionsDB.txt");
		//arrayList that represents a receipt
		lines = new ArrayList <String>(); 
		lines.add("RENT|13101977 13:00|1111111111111111|085392132225,639382000393,");
		lines.add("RENT|14101977 14:00|2222222222222222|012300000642,");
		lines.add("RENT|15101977 15:00|3333333333333333|074470034629,");
		
		line =    "RENT|16101977 16:00|4444444444444444|085392132225,";
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		persistance = null;
		lines = null;
		linesRead = null;
		line = null;
	}

	public void testWrite() throws Exception {
		persistance.write(lines);
		persistance.write(line);
		
	}

	public void testRead() throws Exception {
		String tmpLine;
		linesRead = persistance.read();
		Iterator<String> iterator = linesRead.iterator();
		System.out.println("----Begin Contents of Persistance File----");
		while (iterator.hasNext())
		{
			tmpLine = (String)iterator.next();
			System.out.println(tmpLine);
		}
		System.out.println("----End Contents of Persistance File----");
	}
}
