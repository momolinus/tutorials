/**
 *
 */
package de.m_bleil.java_api.datetime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class DateTimeFormatterTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

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

	@Test
	public void testFormatter_dd_MMM_aYear_as_parser_01() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");

		expectedException.expect(DateTimeParseException.class);
		LocalDate.parse("9-Sep-1949", formatter);
	}

	@Test
	public void testFormatter_dd_MMM_aYear_as_parser_02() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

		expectedException.expect(DateTimeParseException.class);
		LocalDate.parse("9-Sep-1949", formatter);
	}

	@Test
	public void testFormatter_dd_MMM_aYear_as_parser_03() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-uuuu");

		expectedException.expect(DateTimeParseException.class);
		LocalDate.parse("9-Sep-1949", formatter);
	}

}
