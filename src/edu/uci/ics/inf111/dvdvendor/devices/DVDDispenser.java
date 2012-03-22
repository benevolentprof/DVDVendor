/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 8, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 * The DVD Dispenser class can simulate the action of dispatching or 
 * receiving an item to the DVD dispenser.
 */

package edu.uci.ics.inf111.dvdvendor.devices;


import java.util.Vector;

/**
 * The DVDDispenser class represents a wrapper for a hardware driver 
 * of the device to receive and dispense the DVDs.  Because we are not really using 
 * such a device, our class provides methods to simulate the device is receiving 
 * or dispensing DVDs.<br>
 * <br>
 * The DVDDispenser uses an <i>Observer</i> (or <i>Listener</i>) design
 * pattern.  Objects which implement the <code>DVDDispenserListener</code>
 * interface may use the <code>attach()</code> method to register with 
 * the DVDDispenser.  When a receive or dispense event occurs, the DVDDispenser
 * reports the event to its observers using their notify methods.
 * In this example, we do not support detaching from the DVDDispenser.<br>
 * <br>    
 *
 */
public class DVDDispenser {
	/**
	 * bar code records the current bar code of the DVD being sensed in the DVDDispenser.
	 */
	private String barCode;

	/**
	 * observers is a Vector of DVDDispenserListeners which will be notified 
	 * of receiving and dispensing events.
	 */
	private Vector<DVDDispenserListener> observers;

	/**
	 * This simple constructor initializes a blank bar code and an empty set of observers.
	 */
	public DVDDispenser() {
		observers = new Vector<DVDDispenserListener>();
		barCode = "";
	}

	/**
	 * Returns the current bar code being registered by the DVD Dispenser reader.
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * receive() is the method we use to simulate the DVD dispenser is
	 * receiving a product.  This is a gross simplification, but will suffice for our system.
	 * @param barCode The bar code read in this event.  
	 */
	public void receive(String barCode) {
		this.barCode = barCode;
		DVDDispenserEvent ddEvent = new DVDDispenserEvent("RECEIVE", this.barCode);	
		notifyObserver(ddEvent); // notify the observer that a RECEIVE event occurred
	}
	/**
	 * dispense() is the method we use to simulate the DVD dispenser is
	 * dispensing a product.  This is a gross simplification, but will suffice for our system.
	 * @param barCode The bar code read in this event.  
	 */
	public void dispense(String barCode) {
		this.barCode = barCode;
		DVDDispenserEvent ddEvent = new DVDDispenserEvent("DISPENSE", this.barCode);		
		notifyObserver(ddEvent); // notify the observer that a DISPENSE event occurred
	}
	
	/**
	 * The blankCode() method reset the bar code of the DVDDispenser
	 */
	public void blankCode() {
		this.barCode = "";
	}

	/**
	 * notifyObserver() passes the information of the event along to the
	 * observer objects as a DVDDispenserEvent, which includes the current bar code in the
	 * the DVD dispenser.  The information is passed
	 * by calling the notifyDVDDispenserEvent() method on the Listener.
	 * @param event An event containing the event type and the current bar code in the dispenser.
	 */
	private void notifyObserver(DVDDispenserEvent event) {
		for (int index = 0; index < observers.size(); index++) {
			observers.get(index).notifyDVDDispenserEvent(this, event);
		}
	}
	
	/**
	 * The attach() method registers a DVDDispenserListener to receive all future DVDDispenserEvents.
	 * @param ddl The Listener (often the calling object) to be attached.
	 */
	public void attach(DVDDispenserListener ddl) {
		if (ddl != null) {
			observers.add(ddl);
		}
	}
}
