/**
 * created at 19.08.2019
 */
package de.m_bleil.java_api;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author M. Comp. Sc. Marcus Bleil<br>
 *         TU Berlin<br>
 *         Arbeits-, Ingenieur- und Organisationspsychologie<br>
 *         Marchstr. 12, 10587 Berlin<br>
 *         marcus.bleil@tu-berlin.de<br>
 *         http://www.aio.tu-berlin.de
 */
public class VarArgs {

	public static int[] function(int... args) {
		return args;
	}

	@Test
	public void testEmptyArgs() {
		int[] result = function();

		assertThat(result, is(notNullValue()));
		assertThat(result.length, is(equalTo(0)));
	}

}
