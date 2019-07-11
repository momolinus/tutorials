/**
 *
 */
package de.m_bleil.java_api.number_formatter;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Test;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class NumberFormatterTest {

	@Test
	public void testDecimalFormat() {
		NumberFormat formatter = new DecimalFormat("000");

		String number = formatter.format(1L);

		assertThat(number, is(equalTo("001")));
	}

	@Test
	public void testDecimalFormat_02() {
		NumberFormat formatter = new DecimalFormat("#.####");

		String number = formatter.format(1.23456789);

		assertThat(number, is(equalTo("1,2346")));
	}

	@Test
	public void testDecimalFormat_03() {

		String number = String.format("%02d", 2);
		assertThat(number, is(equalTo("02")));

		number = String.format("%03d", 2);
		assertThat(number, is(equalTo("002")));
	}

	@Test
	public void testDecimalFormat_04() {
		NumberFormat formatter = new DecimalFormat("#");

		String number = formatter.format(2.0);

		assertThat(number, is(equalTo("2")));
	}

	@Test
	public void testDecimalFormat_05() {
		NumberFormat formatter = new DecimalFormat("#");

		String number = formatter.format(15.5);

		assertThat(number, is(equalTo("16")));
	}
}
