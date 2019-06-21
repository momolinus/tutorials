package de.ing_bleil.tutorials.exceptions;

public class BasicCalculator {

	/**
	 * @throws RuntimeException
	 *             if value < 0.0
	 */
	public double squareRoot(double value) {
		if (value < 0.0) {
			throw new RuntimeException(value
					+ " as value is not permitted, only values >= 0 allowed");
		}
		return Math.sqrt(value);
	}
}
