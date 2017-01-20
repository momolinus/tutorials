/**
 * 11.12.2016
 */
package de.m_bleil.java_api.message;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.text.MessageFormat;

import org.junit.Test;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class MessageFormatTest {

	@Test
	public void testQuoting_01() {
		String result = MessageFormat.format("text1 ''{0}'' text3", "text2");

		assertThat(result, is(equalTo("text1 'text2' text3")));
	}

	@Test
	public void testQuoting_02() {
		String result = MessageFormat.format("text1 '{0}' text3", "text2");

		assertThat(result, is(equalTo("text1 {0} text3")));
	}

	@Test
	public void testQuoting_03() {
		String result = MessageFormat.format("text1 '{'{0}'}' text3", "text2");

		assertThat(result, is(equalTo("text1 {text2} text3")));
	}
}
