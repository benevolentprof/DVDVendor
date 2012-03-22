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

import edu.uci.ics.inf111.dvdvendor.exceptions.InvalidBarCodeException;

/**
 * The BarCode class represents the bar code of the Products
 *
 */
public class BarCode {
	/**
	 * The String representation of the 12-digit code which this object represents. 
	 */
	private String barCodeNumber;

	/**
	 * Creates a BarCode object unless the supplied digit string is illegal.
	 * @param productCode A String of digits corresponding to the bar code. 
	 * @throws InvalidBarCodeException Thrown if the provided String is null, too short, or fails the checksum.
	 */
	public BarCode(String productCode) throws InvalidBarCodeException {
		try {
			checkSum(productCode);
			barCodeNumber = productCode;
		} catch (InvalidBarCodeException iBCe) {
			throw (iBCe);
		}
	}

	/**
	 * An accessor method returning the bar code
	 */
	public String getBarCode() {
		return barCodeNumber;
	}

	/**
	 * Compare another BarCode object to this BarCode object to determine if they are effectively equal.  This occurs if the Strings they represent are identical.
	 * @param comparedCode The BarCode object we are comparing to.
	 * @return <code>true</code> if the String representations are identical, <code>false</code> otherwise.
	 */
	public boolean equals(BarCode comparedCode) {
		return barCodeNumber == comparedCode.getBarCode();
	}

	/**
	 * The hashCode is generally the final character of a BarCode, and is used to determine whether the BarCode is a legal example of its type.
	 * @return An int containing the hash digit.
	 */
	public int hashCode() {
		// return the corresponding integer value of the last character of the BarCode
		return (barCodeNumber
				.charAt(barCodeNumber.length() - 1)) - 48;
	}

	/**
	 * This function checks whether the scanned BarCode is a valid one 
	 * (not in the sense whether it exists in the database, but 
	 * whether the Bar code that is passed in is of correct format to calculate the Checksum.<br>
	 * The checksum is a Modulo 10 calculation.<br>
	 * 1. Add the values of the digits in positions 1, 3, 5, 7, 9, and 11.<br>
	 * 2. Multiply this result by 3.<br>
	 * 3. Add the values of the digits in positions 2, 4, 6, 8, and 10.<br>
	 * 4. Sum the results of steps 2 and 3.<br>
	 * 5. The check character is the smallest number which, when added to the
	 * result in step 4, produces a multiple of 10.<br>
	 * Example: Assume the bar code data = 01234567890<br>
	 * 1. 0 + 2 + 4 + 6 + 8 + 0 = 20<br>
	 * 2. 20 x 3 = 60<br>
	 * 3. 1 + 3 + 5 + 7 + 9 = 25<br>
	 * 4. 60 + 25 = 85<br>
	 * 5. 85 + X = 90 (next highest multiple of 10), therefore X = 5 (checksum)<br>
	 * @param code The String to be checked
	 * @throws InvalidBarCodeException Thrown if the supplied code is of incorrect length or has an incorrect checksum.
	 */
	private void checkSum(String code) throws InvalidBarCodeException {
		char[] charsOfBarCode;
		int[] digitsOfBarCode = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int oddSum = 0; // sum of numbers at odd positions of the Bar Code
		int evenSum = 0; // sum of numbers at even positions of the Bar Code

		if (code.length() != 12) {
			throw (new InvalidBarCodeException("Bar Code length must be 12 digits"));
		} else {
			charsOfBarCode = code.toCharArray(); // convert String to chars

			// change to integers
			for (int i = 0; i < 12; i++) {
				digitsOfBarCode[i] = charsOfBarCode[i] - 48;
			}

			// apply the checksum algorithm
			oddSum = digitsOfBarCode[0] + digitsOfBarCode[2] + digitsOfBarCode[4]
					+ digitsOfBarCode[6] + digitsOfBarCode[8] + digitsOfBarCode[10];
			evenSum = digitsOfBarCode[1] + digitsOfBarCode[3] + digitsOfBarCode[5]
					+ digitsOfBarCode[7] + digitsOfBarCode[9];
			if ((oddSum * 3 + evenSum + digitsOfBarCode[11]) % 10 != 0) {
				throw (new InvalidBarCodeException("Bar Code checksum error"));
			}
		}
	}
}
