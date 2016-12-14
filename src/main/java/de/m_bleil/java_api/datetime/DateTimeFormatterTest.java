/**
 *
 */
package de.m_bleil.java_api.datetime;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class DateTimeFormatterTest {

	@Test
	public void testFormatter_dd_LL_YYYY() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LL-YYYY");
		LocalDate date = LocalDate.of(1949, Month.SEPTEMBER, 30);

		assertThat(date.toString(), is(equalTo("1949-09-30")));
		assertThat(formatter.format(date), is(equalTo("30-09-1949")));
	}

	@Test
	public void testFormatter_dd_LL_yyyy() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LL-yyyy");
		LocalDate date = LocalDate.of(1949, Month.SEPTEMBER, 30);

		String dateString = formatter.format(date);

		assertThat(dateString, is(equalTo("30-09-1949")));
	}

	@Test
	public void testFormatter_dd_MMM_yyyy() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate date = LocalDate.of(1949, Month.SEPTEMBER, 30);

		String dateString = formatter.format(date);

		assertThat(dateString, is(equalTo("30-Sep-1949")));
	}
}
