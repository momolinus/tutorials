/**
 * 
 */
package de.m_bleil.api_inspection.comparison;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Comparator;

import org.junit.Test;

/**
 * This class contains tests to inspect, how string comparison works.
 * 
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class StringCompareTest {

	/**
	 * Method shows how empty strings are compared using {@linkplain String#compareTo(String)}.
	 */
	@Test
	public void testComparisonsForEmptyStrings() {
		int comparisonValue;

		comparisonValue = "".compareTo("");
		assertThat(comparisonValue, is(equalTo(0)));

		comparisonValue = "".compareTo("1");
		assertThat(comparisonValue, is(equalTo(-1)));
		comparisonValue = "1".compareTo("");
		assertThat(comparisonValue, is(equalTo(1)));

		comparisonValue = "".compareTo("XYZ");
		assertThat(comparisonValue, is(equalTo(-3)));
		comparisonValue = "XYZ".compareTo("");
		assertThat(comparisonValue, is(equalTo(3)));

		comparisonValue = "".compareTo("Z");
		assertThat(comparisonValue, is(equalTo(-1)));
		comparisonValue = "Z".compareTo("");
		assertThat(comparisonValue, is(equalTo(1)));

		comparisonValue = "".compareTo("abcdef");
		assertThat(comparisonValue, is(equalTo(-6)));
		comparisonValue = "abcdef".compareTo("");
		assertThat(comparisonValue, is(equalTo(6)));
	}

	@Test
	public void testComparisonsForEmptyStringsWithComparator() {
		Comparator<String> comparator;
		int comparisonValue;

		comparator = Comparator.naturalOrder();

		comparisonValue = comparator.compare("", "1");
		assertThat(comparisonValue, is(equalTo(-1)));
		comparisonValue = comparator.compare("", "1");
		assertThat(comparisonValue, is(equalTo(-1)));

		comparisonValue = comparator.compare("", "abcdef");
		assertThat(comparisonValue, is(equalTo(-6)));
		comparisonValue = comparator.compare("abcdef", "");
		assertThat(comparisonValue, is(equalTo(6)));
	}
}
