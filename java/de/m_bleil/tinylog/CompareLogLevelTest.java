/**
 * created at 08.02.2017
 */
package de.m_bleil.tinylog;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.pmw.tinylog.Level;

/**
 * @author Marcus Bleil, www.m-bleil.de
 */
public class CompareLogLevelTest {

	@Test
	public void test() {

		assertThat(Level.DEBUG.compareTo(Level.TRACE), is(equalTo(1)));
		assertThat(Level.DEBUG.compareTo(Level.DEBUG), is(equalTo(0)));
		assertThat(Level.DEBUG.compareTo(Level.INFO), is(equalTo(-1)));
		assertThat(Level.DEBUG.compareTo(Level.WARNING), is(equalTo(-2)));
	}

}
