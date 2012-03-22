/*
 * 
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 8, 2009
 * 
 * Copyright, 2009 University of California. 
 */
package edu.uci.ics.inf111.dvdvendor.devices;

/**
 * The DVDDispenserEvent class serves to encapsulate the information associated
 * with a change in the state of the DVDDispenser, so that it can easily be passed 
 * to the DVDDispenserListeners.
 *
 */
public class DVDDispenserEvent {
	/**
	 * The event type detected by the DVDDispenser.
	 */
	private String eventType;

	/**
	 * The bar code read by the DVDDispenser.
	 */
	private String barCode;

	/**
	 * Create a DVDDispenserEvent.  The object does not change after construction.
	 * @param eventType	Event type detected by the DVDDispenser
	 * @param barCode	Bar code read by the DVDDispenser
	 */
	public DVDDispenserEvent(String eventType, String barCode) {
		this.eventType = eventType;
		this.barCode = barCode;
	}
	
	/**
	 * Returns the event type detected by the DVDDispenser. 
	 */
	public String getEventType() {
		return this.eventType;		
	}
	
	/**
	 * Returns the bar code read by the DVDDispenser. 
	 */
	public String getBarCode() {
		return this.barCode;
	}

}
