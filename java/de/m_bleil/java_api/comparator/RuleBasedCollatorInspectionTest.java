/**
 * created 02.05.2017
 */
package de.m_bleil.java_api.comparator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.*;

import org.junit.Test;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class RuleBasedCollatorInspectionTest {

	@Test
	public void inspectSimpleRule() throws ParseException {
		// from JavaDoc: RuleBasedCollator maps characters to sort keys
		RuleBasedCollator collator = new RuleBasedCollator("< vp < a < b < c");

		List<String> columns = new ArrayList<>();
		columns.add("a");
		columns.add("c");
		columns.add("vp");
		columns.add("x");

		assertThat(columns.get(0), is(equalTo("a")));
		assertThat(columns.get(1), is(equalTo("c")));
		assertThat(columns.get(2), is(equalTo("vp")));

		Collections.sort(columns, collator);

		assertThat(columns.get(0), is(equalTo("vp")));
		assertThat(columns.get(1), is(equalTo("a")));
		assertThat(columns.get(2), is(equalTo("c")));

	}
}
