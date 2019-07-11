/**
 * created at 27.06.2019
 */
package de.m_bleil.java_api.string;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Test;

/**
 * @author M. Comp. Sc. Marcus Bleil<br>
 *         TU Berlin<br>
 *         Arbeits-, Ingenieur- und Organisationspsychologie<br>
 *         Marchstr. 12, 10587 Berlin<br>
 *         marcus.bleil@tu-berlin.de<br>
 *         http://www.aio.tu-berlin.de
 */
public class StringFormatTest {

	@Test
	public void testFormat_01() {
		String string = String.format(Locale.GERMAN, "Text %.2f %%", 2.5);

		assertThat(string, is(equalTo("Text 2,50 %")));
	}

	@Test
	public void testFormat_02() {
		String string = String.format("Text %.0f", 2.0);

		assertThat(string, is(equalTo("Text 2")));
	}

	@Test
	public void testFormat_03() {
		String string = String.format("Text %.0f", 2.5);

		assertThat(string, is(equalTo("Text 3")));
	}
}
