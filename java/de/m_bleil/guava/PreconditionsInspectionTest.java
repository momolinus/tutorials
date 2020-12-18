/**
 *
 */
package de.m_bleil.guava;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Objects;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class PreconditionsInspectionTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCheckNotNull() {

		expectedException.expect(NullPointerException.class);
		expectedException
			.expectMessage(is(equalTo("parameter must not be null, caused by cond1 = 3 and cond2 = 6")));
		Object parameter = null;
		int cond1 = 3, cond2 = 6;

		checkNotNull(	parameter, "parameter must not be null, caused by cond1 = %s and cond2 = %s",
						cond1, cond2);
	}

	/**
	 * demonstrates that %s is the <strong>only</strong> correct placeholder
	 */
	@Test
	public void testCheckNotNullFormatWithError() {

		expectedException.expect(NullPointerException.class);
		expectedException
			.expectMessage(is(equalTo("parameter must not be null, caused by error1 = %d and error2 = %d [3, 5]")));
		Object parameter = null;
		int errorCode1 = 3, errorCode2 = 5;

		checkNotNull(	parameter, "parameter must not be null, caused by error1 = %d and error2 = %d",
						errorCode1, errorCode2);
	}

	// TODO inspection test for unmatched arguments and placeholder

	/**
	 * demonstrates that %s is the correct placeholder
	 */
	@Test
	public void testCheckNotNullFormatWithoutError() {

		expectedException.expect(NullPointerException.class);
		expectedException
			.expectMessage(is(equalTo("parameter must not be null, caused by error1 = 3 and error2 = 5")));
		Object parameter = null;
		int errorCode1 = 3, errorCode2 = 5;

		checkNotNull(	parameter, "parameter must not be null, caused by error1 = %s and error2 = %s",
						errorCode1, errorCode2);
	}

	@Test
	public void testRequireNonNull() {

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage(is(equalTo("parameter must not be null")));
		Object parameter = null;

		Objects.requireNonNull(parameter, "parameter must not be null");
	}
}
