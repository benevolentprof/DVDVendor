/*
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
 * The DVDDispenserListener interface must be implemented by any class which 
 * wishes to attach() to the DVDDispenser to receive DVDDispenserEvents.
 *
 */
public interface DVDDispenserListener {
	/**
	 * This method is called on the Listener whenever a DVD dispenser event (receive or dispense) occurs.
	 * @param dd	The DVDDispenser which is sending the event (in case the Listener is registered to more than one DVDDispenser)
	 * @param event	The DVDDispenserEvent, which encapsulates information about the state change in the DVDDispenser which prompted the event.
	 */
	public void notifyDVDDispenserEvent(DVDDispenser dd, DVDDispenserEvent event);
}
