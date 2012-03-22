/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 8, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 */

package edu.uci.ics.inf111.dvdvendor.app;
/**
 * A DVD represents a single bar-code-bearing product in the point of sale. This class
 * contains the main characteristics of a DVD for rent.
 *
 */
public class DVD implements Product {

	/**
	 * The bar code for this DVD. 
	 */
	private BarCode barCode;	
	/**
	 * The title for this DVD. 
	 */
	private String title;	
	/**
	 * The price for this DVD. 
	 */
	private Double price;	
	/**
	 * The plot for this DVD. 
	 */
	private String plot;
	/**
	 * The genre for this DVD. 
	 */
	private Utils.genre[] genre;	
	/**
	 * The running time for this DVD. 
	 */
	private String runningTime;
	/**
	 * The starring for this DVD. 
	 */
	private String starring;
	
	
	/**
	 * This constructor stores all relevant details of the product, which can
	 * be retrieved using accessor methods.
	 * @param barCode	A unique 12-digit bar code for the DVD.
	 * @param title		A title for the DVD.
	 * @param price		A price for the DVD.
	 * @param plot		A plot for the DVD.
	 * @param genre 	A list of genres for the DVD.
	 * @param runningTime 	Running time for the DVD.
	 * @param starring		Starring for the DVD.
	 */
	public DVD(BarCode barCode, String title,
			double price, String plot, Utils.genre[] genre, String runningTime, 
			String starring) {
		this.barCode = barCode;
		this.title = title;
		this.price = price;
		this.plot = plot;
		this.genre = genre;
		this.runningTime = runningTime;
		this.starring = starring;
		
	}
	
	/**
	 * An accessor method which returns the bar code for the DVD.
	 */
	public BarCode getBarCode() {
		return barCode;
	}

	/**
	 * An accessor method which returns the price for the DVD.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * An accessor method which sets the title for the DVD.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * An accessor method which returns the title for the DVD.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * An accessor method which sets the plot for the DVD.
	 */
	public void setPlot(String plot) {
		this.plot = plot;
	}

	/**
	 * An accessor method which returns the plot for the DVD.
	 */
	public String getPlot() {
		return plot;
	}


	/**
	 * An accessor method which sets array of genres for the DVD.
	 */
	public void setGenre(Utils.genre[] genre) {
		this.genre = genre;
	}

	/**
	 * An accessor method which returns the array of genres for the DVD.
	 */
	public Utils.genre[] getGenre() {
		return genre;
	}

	/**
	 * An accessor method which sets the running time for the DVD.
	 */
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	/**
	 * An accessor method which returns the running time for the DVD.
	 */
	public String getRunningTime() {
		return runningTime;
	}

	/**
	 * An accessor method which sets the starring for the DVD.
	 */
	public void setStarring(String starring) {
		this.starring = starring;
	}

	/**
	 * An accessor method which returns the starring for the DVD.
	 */
	public String getStarring() {
		return starring;
	}
    
}
