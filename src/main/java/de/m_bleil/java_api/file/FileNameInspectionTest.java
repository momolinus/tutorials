/**
 * created at 10.03.2017
 */
package de.m_bleil.java_api.file;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import java.io.File;

import org.junit.Test;

/**
 * @author Marcus Bleil, www.m-bleil.de
 */
public class FileNameInspectionTest {

	@Test
	public void testGetName() {
		File aFile = new File("path1/path2/file.txt");

		assertThat(aFile.getParent(), is(equalTo("path1\\path2")));
		assertThat(aFile.getName(), is(equalTo("file.txt")));
	}
}
