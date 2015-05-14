package de.ing_bleil.tutorials.exceptions;

import org.junit.Before;
import org.junit.Test;

public class BasicCalculatorTest {

	private BasicCalculator calculator;

	@Before
	public void setUp() throws Exception {
		calculator = new BasicCalculator();
	}

	@Test(expected = RuntimeException.class)
	public void testSquareRoot() {
		calculator.squareRoot(-0.1);
	}
}
