package de.m_bleil;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * The Class ModuloTest.
 */
public class ModuloTest {

	@Test
	public void testModuloNull() {
		assertThat(0 % 1, is(equalTo(0)));
		assertThat(0 % 2, is(equalTo(0)));
		assertThat(0 % 3, is(equalTo(0)));
		assertThat(0 % 5, is(equalTo(0)));
	}

	@Test
	public void testModulo10() {

		assertThat(-11 % 10, is(equalTo(-1)));
		assertThat(-10 % 10, is(equalTo(0)));
		assertThat(-9 % 10, is(equalTo(-9)));

		assertThat(0 % 10, is(equalTo(0)));

		assertThat(9 % 10, is(equalTo(9)));
		assertThat(10 % 10, is(equalTo(0)));
		assertThat(11 % 10, is(equalTo(1)));
	}
}
