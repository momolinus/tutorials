/**
 *
 */
package de.m_bleil.java_api.streams;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class CollectorsTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void test() {
		String[] tupples = {};

		String tuppelsJoined = Arrays.stream(tupples).collect(Collectors.joining("_"));

		assertThat(tuppelsJoined, is(equalTo("")));
	}

	@Test
	public void test2() {
		String[] tupples = null;

		expectedException.expect(NullPointerException.class);
		Arrays.stream(tupples).collect(Collectors.joining("_"));
	}
}
