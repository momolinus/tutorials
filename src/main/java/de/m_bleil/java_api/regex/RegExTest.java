/**
 * created at 04.05.2017
 */
package de.m_bleil.java_api.regex;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Marcus Bleil, www.m-bleil.de
 */
public class RegExTest {

	private static String text;

	@BeforeClass
	public static void readText() throws IOException {
		InputStream inputStream = RegExTest.class.getResourceAsStream("hallo-welt.txt");

		int character = 0;
		StringBuffer fileContent = new StringBuffer();
		while ((character = inputStream.read()) != -1) {
			fileContent.append((char) character);
		}

		text = fileContent.toString();
	}

	@Test
	public void testThatFileContentIsValid() {
		assertThat(text, startsWith("Hallo"));
		assertThat(text, endsWith("Welt 2"));
		assertThat(text, containsString("\n"));
	}

	@Test
	public void testThatOne_LF_isPresent() {
		Matcher matcher = Pattern.compile("\\012").matcher(text);

		int matches = 0;
		while (matcher.find()) {
			matches++;
		}
		assertThat("lookup for LF (\\012)", matches, is(equalTo(1)));
	}

	@Test
	public void testThatOne_CR_isPresent() {
		Matcher matcher = Pattern.compile("\\015").matcher(text);

		int matches = 0;
		while (matcher.find()) {
			matches++;
		}
		assertThat("lookup for CR (\\015)", matches, is(equalTo(1)));
	}

	@Test
	public void testThatOne_NL_isPresent() {
		Matcher matcher = Pattern.compile("\\n").matcher(text);

		int matches = 0;
		while (matcher.find()) {
			matches++;
		}
		assertThat("lookup for Newline (\\n)", matches, is(equalTo(1)));
	}

	@Test
	public void testHowAllNewlinesReplaced() {
		String textWithoutNewLines = text.replaceAll("\\n", "");
		assertThat(textWithoutNewLines, is(equalTo("Hallo\rWelt 2")));

		textWithoutNewLines = text.replaceAll("\\n\\r", "");
		assertThat(textWithoutNewLines, is(equalTo("Hallo\r\nWelt 2")));

		textWithoutNewLines = text.replaceAll("\\n|\\r", "");
		assertThat(textWithoutNewLines, is(equalTo("HalloWelt 2")));

		textWithoutNewLines = text.replaceAll("(\\n|\\r)", "");
		assertThat(textWithoutNewLines, is(equalTo("HalloWelt 2")));
	}

	@Test
	public void testHowAllNewlinesAndWhitespacesReplaced() {
		String textWithoutNewLines = text.replaceAll("\\n|\\r|\\s", "");
		assertThat(textWithoutNewLines, is(equalTo("HalloWelt2")));

		textWithoutNewLines = text.replaceAll("(\\n|\\r|\\s)", "");
		assertThat(textWithoutNewLines, is(equalTo("HalloWelt2")));
	}
}
