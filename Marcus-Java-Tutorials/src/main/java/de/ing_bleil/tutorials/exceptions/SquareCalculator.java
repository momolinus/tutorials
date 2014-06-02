package de.ing_bleil.tutorials.exceptions;

public class SquareCalculator {

	private BasicCalculator basicCalculator;

	public SquareCalculator() {
		basicCalculator = new BasicCalculator();
	}

	/**
	 * the most bad implementation, the low level module throws an exception,
	 * which is passed through the top level modul
	 */
	public double sideLengthPropagatingException(double area) {
		return basicCalculator.squareRoot(area);
	}

	public double sideLengthTranslatingException(double area) {
		try {
			return basicCalculator.squareRoot(area);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException(area
					+ " is not permitted for area, area must be >= 0");
		}

	}

	public double sideLengthTranslatingExceptionByChaining(double area) {
		try {
			return basicCalculator.squareRoot(area);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public double sideLength(double area) throws SquareCalculatorExceptions {
		if (area < 0.0) {
			throw new SquareCalculatorExceptions(area
					+ " as area is not permitted, area must be >= 0.0");
		}

		return basicCalculator.squareRoot(area);

	}

	public double sideLengthSilent(double area) {
		try {
			return basicCalculator.squareRoot(area);
		} catch (RuntimeException e) {
			System.out.println("WARNING: passed area was negativ: " + area);
			return basicCalculator.squareRoot(-area);
		}
	}
}
