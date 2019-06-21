/**
 * created at 26.11.2018
 */
package de.m_bleil.java_api.nio_file;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;


import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * @author Marcus Bleil<br>
 */
public class FileGetNameInspectionTest {

	@Test
	public void testGetName() {
		Path path = Paths.get("/path1/path2/path3/file.txt");
		Path parent = path.getParent();

		assertThat(parent.toString(), is(equalTo("\\path1\\path2\\path3")));
		assertThat(parent.getFileName().toString(), is(equalTo("path3")));
	}

}
