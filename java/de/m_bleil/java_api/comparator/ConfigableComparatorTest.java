/**
 *
 */
package de.m_bleil.java_api.comparator;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class ConfigableComparatorTest {

	@Test
	public void test() {

		ConfigableComparator comparator = new ConfigableComparator("vp");

		List<String> columns = new ArrayList<>();
		columns.add("a");
		columns.add("c");
		columns.add("vp");
		columns.add("x");

		assertThat(columns.get(0), is(equalTo("a")));
		assertThat(columns.get(1), is(equalTo("c")));
		assertThat(columns.get(2), is(equalTo("vp")));

		Collections.sort(columns, comparator);

		assertThat(columns.get(0), is(equalTo("vp")));
		assertThat(columns.get(1), is(equalTo("a")));
		assertThat(columns.get(2), is(equalTo("c")));

	}

	@Test
	public void test2() {

		ConfigableComparator comparator = new ConfigableComparator("VP_ID");

		List<String> columns = new ArrayList<>();
		columns.add("a");
		columns.add("c");
		columns.add("VP_ID");
		columns.add("x");

		assertThat(columns.get(0), is(equalTo("a")));
		assertThat(columns.get(1), is(equalTo("c")));
		assertThat(columns.get(2), is(equalTo("VP_ID")));

		Collections.sort(columns, comparator);

		assertThat(columns.get(0), is(equalTo("VP_ID")));
		assertThat(columns.get(1), is(equalTo("a")));
		assertThat(columns.get(2), is(equalTo("c")));

	}

	@Test
	public void test3() {

		ConfigableComparator comparator = new ConfigableComparator("VP_ID", "bed");

		List<String> columns = new ArrayList<>();
		columns.add("a");
		columns.add("c");
		columns.add("bed");
		columns.add("VP_ID");
		columns.add("x");

		assertThat(columns.get(0), is(equalTo("a")));
		assertThat(columns.get(1), is(equalTo("c")));
		assertThat(columns.get(2), is(equalTo("bed")));
		assertThat(columns.get(3), is(equalTo("VP_ID")));

		Collections.sort(columns, comparator);

		assertThat(columns.get(0), is(equalTo("VP_ID")));
		assertThat(columns.get(1), is(equalTo("bed")));
		assertThat(columns.get(2), is(equalTo("a")));
		assertThat(columns.get(3), is(equalTo("c")));

	}
}
