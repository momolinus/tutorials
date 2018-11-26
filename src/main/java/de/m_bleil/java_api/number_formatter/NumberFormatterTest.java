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
}
