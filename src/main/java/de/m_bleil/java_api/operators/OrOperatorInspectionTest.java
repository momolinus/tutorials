/**
 * created at 02.08.2017
 */
package de.m_bleil.java_api.operators;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Marcus Bleil, www.m-bleil.de
 */
public class OrOperatorInspectionTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {}

	@SuppressWarnings("null")
	@Test
	public void testLogicalOr() {
		Object o1 = null;

		if (o1 == null || o1.toString().equals("")) {
			assertTrue(true);
		}

		boolean exception = false;
		try {
			if (o1 == null | o1.toString().equals("")) {
				assertTrue("nullpointer exception expected", false);
			}
		}
		catch (NullPointerException e) {
			exception = true;
		}

		assertThat(exception, is(true));
	}

}
