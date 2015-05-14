package de.ing_bleil.tutorials.exceptions;

public class SquareCalculatorSamples {

	public static void main(String[] args) {
		SquareCalculator calculator = new SquareCalculator();

		try {
			calculator.sideLengthPropagatingException(-40.0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		try {
			calculator.sideLengthTranslatingException(-40.0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		try {
			calculator.sideLengthTranslatingExceptionByChaining(-40.0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		try {
			calculator.sideLength(-40.0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		calculator.sideLengthSilent(-40.0);
	}

}
