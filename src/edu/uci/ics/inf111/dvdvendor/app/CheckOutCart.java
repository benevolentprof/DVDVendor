/*
 * Creator: Rosalva Gallardo-Valencia
 * Based on code created by Susan Elliott Sim
 * Course: Inf111, Winter 2009
 * 
 * Created on January 8, 2009
 * 
 * Copyright, 2009 University of California. 
 * 
 * The CheckOutCart class maintains the items added as well as the total cost of the items.
 */
package edu.uci.ics.inf111.dvdvendor.app;


import java.util.*;

/**
 * The CheckOutCart class stores a Vector of Products which
 * the customer has added so far in the transaction.
 *
 */
public class CheckOutCart {
	/**
	 * A Vector of Products.
	 */
	private Vector<Product> items; // stores the items added

	/**
	 * The cost of the items in the cart
	 */
	private double totalCost;

	/**
	 * Creates a new CheckOutCart with an empty item list and 0 cost.
	 */
	public CheckOutCart() {
		items = new Vector<Product>();
		totalCost = 0;
	}
	/**
	 * Accessor method which sets the cost of the items in the cart.
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	/**
	 * Accessor method which returns the cost of the items in the cart.
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * Add a single item to the cart, and add its cost to the running totals.
	 * @param newItem
	 */
	public void addItemToCart(Product newItem) {
		items.add(newItem);
		totalCost = totalCost + newItem.getPrice();
	}
	
	/**
	 * This method looks up a product in the CheckOutCart.  
	 * @param item	The item that represents the product.
	 * @return	The Product object of the found product, or null if no such product in the CheckOutCart.
	 */
	public Product lookUpItem(Product item) {
		Enumeration<Product> enumItems = items.elements();
		Product tmpProduct;
		while (enumItems.hasMoreElements()) {
			tmpProduct = enumItems.nextElement();
			if (tmpProduct.getBarCode().getBarCode().equals(item.getBarCode().getBarCode())) {
				return tmpProduct;								
			}
		}
		return null;
	}

	/**
	 * This method returns an enumeration of the Products in the cart.  We do not
	 * return the Vector since we don't want external code to alter our cart.
	 */
	public Enumeration<Product> listItems() {
		Enumeration<Product> enumItems = items.elements();
		return enumItems;
	}
}

