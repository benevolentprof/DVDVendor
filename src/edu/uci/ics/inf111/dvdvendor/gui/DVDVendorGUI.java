/*
 * Creator: Rosalva Gallardo-Valencia
 * Course: Inf111, Winter 2009
 * 
 * Created on January 14, 2009
 * 
 * Copyright, 2009 University of California. 
 *
 *
 */
package edu.uci.ics.inf111.dvdvendor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import edu.uci.ics.inf111.dvdvendor.app.DVDVendor;
import edu.uci.ics.inf111.dvdvendor.app.Product;
import edu.uci.ics.inf111.dvdvendor.exceptions.AddWhileDispensingException;
import edu.uci.ics.inf111.dvdvendor.exceptions.AddWhilePayingException;
import edu.uci.ics.inf111.dvdvendor.exceptions.CalculateWithNoItemException;
import edu.uci.ics.inf111.dvdvendor.exceptions.DispenseWhileAddingException;
import edu.uci.ics.inf111.dvdvendor.exceptions.IncorrectStateException;
import edu.uci.ics.inf111.dvdvendor.exceptions.InvalidProductException;
import edu.uci.ics.inf111.dvdvendor.exceptions.PayWhileDispensingException;
import edu.uci.ics.inf111.dvdvendor.exceptions.PayWithNoItemsException;
import edu.uci.ics.inf111.dvdvendor.exceptions.ProductAlreadyInCheckOutCartException;
import edu.uci.ics.inf111.dvdvendor.exceptions.ProductAlreadyRentedException;
import edu.uci.ics.inf111.dvdvendor.exceptions.ProductNotRentedException;
import edu.uci.ics.inf111.dvdvendor.app.Utils;

/**
 * The DVDVendorGui class handles the Graphical User Interface for the DVD Vendor 
 * system. It allows the user to do the following actions in the system: 
 * Rent DVDs and Return DVDs.
 * Application messages, including exceptions, will be shown in the Messages section of the
 * screen.
 */
public class DVDVendorGUI extends JPanel implements ActionListener{
    /**
	 * Class serial version
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * dvdVendor object that will be used for the transactions
	 */   
    protected DVDVendor dvdVendor = null;
	/**
	 * actions object that contains the actions for the DVD Vendor system
	 */ 
    protected Actions actions = new Actions();
	/**
	 * String for the DVD Bar Code label
	 */
	protected static final String barCodeLabelString = "DVD Bar Code";
	/**
	 * String for the Card Number label
	 */
	protected static final String cardNumberLabelString = "Card Number";	
	/**
	 * String for the StartUp Exception label
	 */
	protected static final String startUpExceptionLabelString = "An exception ocurred in startup: ";

	/**
	 * Button for Rent action
	 */
	protected JButton rentButton;
	/**
	 * Button for Return action
	 */
	protected JButton returnButton;
	/**
	 * Button for Add DVD action
	 */
	protected JButton addDVDButton;
	/**
	 * Button for Dispense action
	 */
	protected JButton dispenseButton;
	/**
	 * Button for Calculate Charges action
	 */
	protected JButton calculateChargesButton;
	/**
	 * Button for Return DVD action
	 */
	protected JButton returnDVDButton;
 
	/**
	 * Button for Main Menu action in Rent Transaction
	 */
	protected JButton mainMenuRentButton;
	/**
	 * Button for Main Menu action in Return Transaction
	 */
	protected JButton mainMenuReturnButton;
	/**
	 * Button for Pay for Items action
	 */
	protected JButton payButton;

    /**
	 * Text Field for Card Number
	 */	
    protected JTextField cardNumberTextField; 

	/**
	 * Text Area for application messages
	 */	
    protected JTextArea messagesTextArea;
    /**
	 * ComboBox for Products to Rent
	 */	    
    protected JComboBox productsRentComboBox;
    /**
	 * ComboBox for Products to Return
	 */	   
    protected JComboBox productsReturnComboBox;
    /**
	 * Panel for the Rent Transaction
	 */	
    protected JPanel rentPane;
    /**
	 * Panel for the Return Transaction
	 */   
    protected JPanel returnPane;
    /**
	 * Panel for choosing Rent or Return Transaction
	 */    
    protected JPanel selectTransactionPane;
    /**
	 * Scroll Panel that will be attached to the Messages Text Area
	 */   
    protected JScrollPane areaScrollPane;

    
	/**
	 * This constructor creates the text fields, labels, and buttons. It organizes all 
	 * these objects in three panels: one for choosing the Transaction,
	 * another for the Rent Transaction and the last one for the 
	 * Return transaction. This three panels will be made visible or invisible
	 * according to the selections of the user. Initially, only the Panel to choose
	 * the transaction will be visible.
	 */    
    public DVDVendorGUI() {
    	
        setLayout(new BorderLayout());
      
        //Text field for Card Number
        cardNumberTextField = new JTextField(10);
        cardNumberTextField.setActionCommand(cardNumberLabelString);
        cardNumberTextField.addActionListener(this);
              
        //Label for Bar Code in the Rent Transaction
        JLabel barCodeRentTextFieldLabel = new JLabel(barCodeLabelString + ": ");
        barCodeRentTextFieldLabel.setLabelFor(productsRentComboBox);
        
        //Label for Bar Code in the Return Transaction
        JLabel barCodeReturnTextFieldLabel = new JLabel(barCodeLabelString + ": ");
        barCodeReturnTextFieldLabel.setLabelFor(productsReturnComboBox);
        
        //Label for Card Number
        JLabel cardNumberTextFieldLabel = new JLabel(cardNumberLabelString + ": ");
        cardNumberTextFieldLabel.setLabelFor(cardNumberTextField);
        
        //Rent DVDs Transaction Button
        rentButton = new JButton("Rent DVDs");
        rentButton.setVerticalTextPosition(AbstractButton.CENTER);
        rentButton.setHorizontalTextPosition(AbstractButton.CENTER);
        rentButton.setPreferredSize(new Dimension(170, 170));
        rentButton.setActionCommand("rentTransaction");
        rentButton.addActionListener(this);
        
        //Return DVDs Transaction Button
        returnButton = new JButton("Return DVDs");
        returnButton.setVerticalTextPosition(AbstractButton.CENTER);
        returnButton.setHorizontalTextPosition(AbstractButton.CENTER);
        returnButton.setPreferredSize(new Dimension(170, 170));
        returnButton.setActionCommand("returnTransaction");
        returnButton.addActionListener(this);

        //Add DVD Button
        addDVDButton = new JButton("Add DVD");
        addDVDButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        addDVDButton.setHorizontalTextPosition(AbstractButton.CENTER);
        addDVDButton.setActionCommand("addDVD");
        addDVDButton.addActionListener(this);
        
        //Dispense Button
        dispenseButton = new JButton("Dispense");
        dispenseButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        dispenseButton.setHorizontalTextPosition(AbstractButton.CENTER);
        dispenseButton.setActionCommand("dispenseDVD");
        dispenseButton.addActionListener(this);
        
        //Return DVD Button
        returnDVDButton = new JButton("Return DVD");
        returnDVDButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        returnDVDButton.setHorizontalTextPosition(AbstractButton.CENTER);
        returnDVDButton.setActionCommand("returnDVD");
        returnDVDButton.addActionListener(this);
        
        //Calculate Charges Button
        calculateChargesButton = new JButton("Calculate Charges");
        calculateChargesButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        calculateChargesButton.setHorizontalTextPosition(AbstractButton.CENTER);
        calculateChargesButton.setActionCommand("calculateCharges");
        calculateChargesButton.addActionListener(this);
                 
        //Pay Button
        payButton = new JButton("Pay");
        payButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        payButton.setHorizontalTextPosition(AbstractButton.CENTER);
        payButton.setActionCommand("payItems");
        payButton.addActionListener(this);
  
        //Get Codes and Titles from the Database of Products. This information
        //will be shown in the combo boxes for Rent and Return Transactions
        String[] codesAndTitles = new String[]{};
        String startUpException = "";
        //If there is an exception, this will be captured in a String and then it will
        //be shown in the main screen.
        try {
        	codesAndTitles = Utils.getBarCodesAndTitlesFromProductDB();
        
        } catch (Exception e) {
        	startUpException = e.getMessage();
        }
        
        //Label for Startup Exception that includes the exception text returned by the method
        // to get the codes and titles from the ProductDB
        JLabel startUpExceptionLabel = new JLabel(startUpExceptionLabelString + 
        		startUpException);
               
        //ComboBox for Products to Rent
        productsRentComboBox = new JComboBox(codesAndTitles);
        productsRentComboBox.addActionListener(this);
        
        //ComboBox for Products to Return
        productsReturnComboBox = new JComboBox(codesAndTitles);
        productsReturnComboBox.addActionListener(this);
        
        //Main Menu Button for Rent Transaction
        mainMenuRentButton = new JButton("Main Menu");
        mainMenuRentButton.setVerticalTextPosition(AbstractButton.CENTER);
        mainMenuRentButton.setHorizontalTextPosition(AbstractButton.CENTER);
        mainMenuRentButton.setActionCommand("mainMenuFromRent");
        mainMenuRentButton.addActionListener(this);
        
        //Main Menu Button for Return Transaction
        mainMenuReturnButton = new JButton("Main Menu");
        mainMenuReturnButton.setVerticalTextPosition(AbstractButton.CENTER);
        mainMenuReturnButton.setHorizontalTextPosition(AbstractButton.CENTER);
        mainMenuReturnButton.setActionCommand("mainMenuFromReturn");
        mainMenuReturnButton.addActionListener(this);
        
        //Begin - Rent Transaction Panel
        
        //Lay out the text controls and the labels
        rentPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        rentPane.setLayout(gridbag);
		c.anchor = GridBagConstraints.EAST;
		
		//Line 1: Add DVD
		c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
		c.fill = GridBagConstraints.NONE;      //reset to default
		c.weightx = 0.0;                       //reset to default
		rentPane.add(barCodeRentTextFieldLabel, c);
		  		
		c.gridwidth = GridBagConstraints.RELATIVE;     
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		rentPane.add(productsRentComboBox, c); 
		
		c.gridwidth = GridBagConstraints.REMAINDER;     //end row
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		rentPane.add(addDVDButton, c);
				
		//Line 2: Pay for Items
		c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
		c.fill = GridBagConstraints.NONE;      //reset to default
		c.weightx = 0.0;                       //reset to default
		rentPane.add(cardNumberTextFieldLabel, c);
		
		c.gridwidth = GridBagConstraints.RELATIVE;     
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		rentPane.add(cardNumberTextField, c);  
		
		c.gridwidth = GridBagConstraints.REMAINDER; //next-to-last
		c.fill = GridBagConstraints.NONE;      //reset to default
		c.weightx = 0.0;                       //reset to default
		rentPane.add(payButton, c);
		
		//Line 3: Dispense 
		c.gridwidth = GridBagConstraints.REMAINDER; //next-to-last
		c.fill = GridBagConstraints.NONE;      //reset to default
		c.weightx = 0.0;                       //reset to default
		rentPane.add(dispenseButton, c);
		
		//Line 4: Main Menu
		c.gridwidth = GridBagConstraints.REMAINDER; //next-to-last
		c.fill = GridBagConstraints.NONE;      //reset to default
		c.weightx = 0.0;                       //reset to default
		rentPane.add(mainMenuRentButton, c);
		
        //Create border for the Rent Panel   
        rentPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Rent DVDs"),
                                BorderFactory.createEmptyBorder(20,100,20,300)));
        
        //End - Rent Transaction Panel
        
        
        //Begin - Return Transaction Panel
        
        //Lay out the text controls and the labels
        returnPane = new JPanel();
        GridBagLayout gridbagReturnPane = new GridBagLayout();
        GridBagConstraints cReturnPane = new GridBagConstraints();
        returnPane.setLayout(gridbagReturnPane);
        cReturnPane.anchor = GridBagConstraints.EAST;
		
		//Line 1: Return DVD
        cReturnPane.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
        cReturnPane.fill = GridBagConstraints.NONE;      //reset to default
        cReturnPane.weightx = 0.0;                       //reset to default
		returnPane.add(barCodeReturnTextFieldLabel, cReturnPane);
		  
		cReturnPane.gridwidth = GridBagConstraints.RELATIVE;     
		cReturnPane.fill = GridBagConstraints.HORIZONTAL;
		cReturnPane.weightx = 1.0;
		returnPane.add(productsReturnComboBox, cReturnPane); 
		
		cReturnPane.gridwidth = GridBagConstraints.REMAINDER;     //end row
		cReturnPane.fill = GridBagConstraints.HORIZONTAL;
		cReturnPane.weightx = 1.0;
		returnPane.add(returnDVDButton, cReturnPane);

		//Line 2: Calculate Charges
		cReturnPane.gridwidth = GridBagConstraints.REMAINDER; //next-to-last
		cReturnPane.fill = GridBagConstraints.NONE;      //reset to default
		cReturnPane.weightx = 0.0;                       //reset to default
		returnPane.add(calculateChargesButton, cReturnPane);
		
		//Line 3: Main Menu
		cReturnPane.gridwidth = GridBagConstraints.REMAINDER; //next-to-last
		cReturnPane.fill = GridBagConstraints.NONE;      //reset to default
		cReturnPane.weightx = 0.0;                       //reset to default
		returnPane.add(mainMenuReturnButton, cReturnPane);
		
        //Create border for Return Panel
        returnPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Return DVDs"),
                                BorderFactory.createEmptyBorder(20,100,20,300)));
        
        //End - Return Transaction Panel
        
        
        //Begin - Select Transaction Panel
        
        //Lay out the text controls and the labels
        selectTransactionPane = new JPanel();
        GridBagLayout gridbagSelectTransaction = new GridBagLayout();
        GridBagConstraints cSelectTransaction = new GridBagConstraints();
        selectTransactionPane.setLayout(gridbagSelectTransaction);
		cSelectTransaction.anchor = GridBagConstraints.EAST;
		
		//Rent Transaction
		cSelectTransaction.gridwidth = GridBagConstraints.REMAINDER; //next-to-last
		cSelectTransaction.fill = GridBagConstraints.CENTER;      //reset to default
		cSelectTransaction.weightx = 0.0;                       //reset to default
		selectTransactionPane.add(rentButton, cSelectTransaction);
		
		//Return Transaction
		cSelectTransaction.gridwidth = GridBagConstraints.REMAINDER; //next-to-last
		cSelectTransaction.fill = GridBagConstraints.CENTER;      //reset to default
		cSelectTransaction.weightx = 0.0;                       //reset to default
		selectTransactionPane.add(returnButton, cSelectTransaction);
		
		//Exception Label
		cSelectTransaction.gridwidth = GridBagConstraints.REMAINDER; //next-to-last
		cSelectTransaction.fill = GridBagConstraints.CENTER;      //reset to default
		cSelectTransaction.weightx = 0.0;                       //reset to default
		selectTransactionPane.add(startUpExceptionLabel, cSelectTransaction);
		startUpExceptionLabel.setVisible(false);
		
		//If there was an exception in startup, show the exception and disable the 
		//Transaction options
		if (!startUpException.equals(""))
		{
			startUpExceptionLabel.setVisible(true);
			rentButton.setEnabled(false);
			returnButton.setEnabled(false);
		}
		
        //Create border for Select Transaction Panel    
		selectTransactionPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Transactions"),
                                BorderFactory.createEmptyBorder(20,100,20,650)));
     	        
        
        
        //End - Select Transaction Panel
        
		
        //Create a text area for the application messages
        messagesTextArea = new JTextArea();
        messagesTextArea.setFont(new Font("Serif", Font.ITALIC, 16));
        messagesTextArea.setLineWrap(true);
        messagesTextArea.setWrapStyleWord(true);
        messagesTextArea.setEditable(false);
        
        //Add scroll to the text area
        areaScrollPane = new JScrollPane(messagesTextArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(700, 250));
        
        //Create border for Messages
        areaScrollPane.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Messages"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                areaScrollPane.getBorder()));


        //Add the Panels for Select Transaction, Rent Transaction, Return Transaction,
        //and the Scroll Panel for Messages
        add(selectTransactionPane, BorderLayout.NORTH);
        add(rentPane, BorderLayout.EAST);
        add(returnPane, BorderLayout.WEST);
        add(areaScrollPane, BorderLayout.SOUTH);
        
        //Initially, only the Select Transaction Panel will be visible. The other panels
        //will be visible according to the options selected by the user.
        areaScrollPane.setVisible(false);
        rentPane.setVisible(false);
        returnPane.setVisible(false);              
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispense thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window
        JFrame frame = new JFrame("DVD Vendor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window
        frame.add(new DVDVendorGUI());

        //Display the window
        frame.pack();
        frame.setVisible(true);       
    }
    
    /**
     * Show the Graphical User Interface for the DVD Vendor application
     */
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	createAndShowGUI();
            }
        });
    }
    
	/**
	 * Method that receives the ActionEvent when a button is pressed in the 
	 * GUI. It calls to the appropriate action in the system and
	 * shows the result of the action in the message text area.
	 * If an exception is raised, this is showed in the message text area
	 * starting with the word EXCEPTION.
	 * @param e ActionEvent captured when user presses a button in the GUI
	 */ 
	public void actionPerformed(ActionEvent e) {

		try{	
			//Initiate Rent Transaction
	        if ("rentTransaction".equals(e.getActionCommand())){
	        	
	        	//Make the Rent Panel and Message Panel visible
	        	this.rentPane.setVisible(true); 
	        	this.returnPane.setVisible(false);
	        	this.areaScrollPane.setVisible(true);
	        	this.selectTransactionPane.setVisible(false);	
	        	
	        	//Initiate Rent Transaction in DVDVendor
	        	dvdVendor = actions.initiateRent();	        	
				messagesTextArea.setText("");
			
			//Initiate Return Transaction	
	        } else if ("returnTransaction".equals(e.getActionCommand())){
	        	
	        	//Make the Return Panel and Message Panel visible
	        	this.rentPane.setVisible(false); 
	        	this.returnPane.setVisible(true);
	        	this.areaScrollPane.setVisible(true);
	        	this.selectTransactionPane.setVisible(false);
	        	
	        	//Initiate Return Transaction in DVDVendor
	        	dvdVendor = actions.initiateReturn();	        	
				messagesTextArea.setText("");
				
			//Return to Main Menu from Rent Transaction or Return Transaction
	        } else if ("mainMenuFromRent".equals(e.getActionCommand()) ||
	        		"mainMenuFromReturn".equals(e.getActionCommand())){
	        	
	        	//Make the Select Transaction Panel visible
	        	this.rentPane.setVisible(false); 
	        	this.returnPane.setVisible(false);
	        	this.areaScrollPane.setVisible(false);
	        	this.selectTransactionPane.setVisible(true);
	        	
	        //Add DVD in Rent Transaction	
	        } else if ("addDVD".equals(e.getActionCommand())){
       		
				Product product = actions.addDVD(dvdVendor, Utils.getBarCodeFromDescription((String)productsRentComboBox.getSelectedItem()));					
				messagesTextArea.setText("Shopping cart " + actions.printShoppingCart(dvdVendor.listItemsInCart()));
				messagesTextArea.append("\n\nTransaction Status " + dvdVendor.getTransactionState());
				messagesTextArea.append("\n\nBar Code DVD " + Utils.getBarCodeFromDescription((String)productsRentComboBox.getSelectedItem()) + " added." );
				
			//Pay for Items in Rent Transaction	
	        } else if ("payItems".equals(e.getActionCommand())){
		        
				messagesTextArea.setText("Shopping cart " + actions.printShoppingCart(dvdVendor.listItemsInCart()));
				actions.payItems(dvdVendor, cardNumberTextField.getText());				
				messagesTextArea.append("\n\nTransaction Status " + dvdVendor.getTransactionState());	
        		String partialText = "\n\nTotal $" + dvdVendor.getTotalCost();
				messagesTextArea.append(partialText + " Paid with Card " + cardNumberTextField.getText());
				cardNumberTextField.setText("");
				
			//Dispense DVD in Rent Transaction				
			} else if ("dispenseDVD".equals(e.getActionCommand())){
 
				String tx = actions.dispenseItems(dvdVendor);
				messagesTextArea.setText("Transaction recorded: " + tx);
				messagesTextArea.append("\n\nTransaction Status " + dvdVendor.getTransactionState());
				messagesTextArea.append("\n\nDispense done sucessfully." );

				//Restart Rent Transaction
				dvdVendor = actions.initiateRent();	
			
			//Return DVD in Return Transaction	
	        } else if ("returnDVD".equals(e.getActionCommand())){
       		
				actions.receiveItem(dvdVendor, Utils.getBarCodeFromDescription((String)productsReturnComboBox.getSelectedItem()));
				messagesTextArea.setText("Product Received.");
				messagesTextArea.append("\n\nTransaction Status " + dvdVendor.getTransactionState());
				messagesTextArea.append("\n\nBar Code DVD " + Utils.getBarCodeFromDescription((String)productsReturnComboBox.getSelectedItem()) + " received." );
				
			//Calculate Charges in Return Transaction		
	        }else if ("calculateCharges".equals(e.getActionCommand())){
       		
				String tx = actions.calculateCharges(dvdVendor, Utils.getBarCodeFromDescription((String)productsReturnComboBox.getSelectedItem()));
				messagesTextArea.setText("Transaction recorded: " + tx);
				messagesTextArea.append("\n\nTransaction Status " + dvdVendor.getTransactionState());
				
				//Restart Return Transaction
				dvdVendor = actions.initiateReturn();					
	        }
	        
	    //Show exception in the text area for Messages
		} catch (InvalidProductException ipe) {
			messagesTextArea.setText("EXCEPTION: " + ipe.getMessage());
		} catch (AddWhileDispensingException awbe) {
        	messagesTextArea.setText("EXCEPTION: " + awbe.getMessage() + " Press the Dispense button to proceed.");
		} catch (AddWhilePayingException awpe) {
			messagesTextArea.setText("EXCEPTION: " + awpe.getMessage());
		} catch (ProductNotRentedException pare) {
			messagesTextArea.setText("EXCEPTION: " + pare.getMessage());
		} catch (PayWithNoItemsException pwnie) {
			messagesTextArea.setText("EXCEPTION: " + pwnie.getMessage());
		} catch (DispenseWhileAddingException dwae) {
			messagesTextArea.setText("EXCEPTION: " + dwae.getMessage());
		} catch (CalculateWithNoItemException cwnie) {
			messagesTextArea.setText("EXCEPTION: " + cwnie.getMessage());
		} catch (PayWhileDispensingException pwde) {
			messagesTextArea.setText("EXCEPTION: " + pwde.getMessage() + " Press the Dispense button to proceed.");
		} catch (ProductAlreadyRentedException pnre) {
			messagesTextArea.setText("EXCEPTION: " + pnre.getMessage());
		} catch (ProductAlreadyInCheckOutCartException paicoce) {
			messagesTextArea.setText("EXCEPTION: " + paicoce.getMessage());
		} catch (IncorrectStateException ise) {
			messagesTextArea.setText("EXCEPTION: Invalid action for current state of DVD Vendor.");
		} catch (Exception exception) {
			messagesTextArea.setText("EXCEPTION: An exception has occurred: "+ exception.getMessage() +". Check console for more details.");
			exception.printStackTrace();
		}
	}	
}
