/**
 * created at 07.09.2017
 */
package de.m_bleil.java_api.path;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Marcus Bleil, www.m-bleil.de
 */
public class InspectPath {

	@Before
	public void setUp() throws Exception {}

	@Test
	public void testResolve() {
		Path p1, p2;

		p1 = Paths.get("home/user/documents");
		p2 = Paths.get("home/root/documents");

		Path relativizeed = p1.relativize(p2);

		assertThat(relativizeed.toString(), is(equalTo("..\\..\\root\\documents")));
	}
}
