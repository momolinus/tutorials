/**
 * 
 */
package de.m_bleil.comparison;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.*;

import org.junit.Test;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class NameCoderTest {

	@Test
	public void testSortNameCodes() {

		Comparator<NameCoder> comparator = Comparator
			.comparingInt(NameCoder::getIndex)
			.thenComparing(NameCoder::getPrefix);

		List<NameCoder> codeC = new ArrayList<>();
		codeC.add(new NameCoder("nM_X_C10"));
		codeC.add(new NameCoder("nM_1_C1"));
		codeC.add(new NameCoder("nP_1_C10"));
		codeC.add(new NameCoder("nP_1_C1"));
		codeC.add(new NameCoder("nz_C1"));
		codeC.add(new NameCoder("nz_C10"));

		assertThat(codeC.get(0).getName(), is(equalTo("nM_X_C10")));
		assertThat(codeC.get(1).getName(), is(equalTo("nM_1_C1")));
		assertThat(codeC.get(2).getName(), is(equalTo("nP_1_C10")));
		assertThat(codeC.get(3).getName(), is(equalTo("nP_1_C1")));
		assertThat(codeC.get(4).getName(), is(equalTo("nz_C1")));
		assertThat(codeC.get(5).getName(), is(equalTo("nz_C10")));

		Collections.sort(codeC, comparator);

		assertThat(codeC.get(0).getName(), is(equalTo("nM_1_C1")));
		assertThat(codeC.get(1).getName(), is(equalTo("nP_1_C1")));
		assertThat(codeC.get(2).getName(), is(equalTo("nz_C1")));
		assertThat(codeC.get(3).getName(), is(equalTo("nM_X_C10")));
		assertThat(codeC.get(4).getName(), is(equalTo("nP_1_C10")));
		assertThat(codeC.get(5).getName(), is(equalTo("nz_C10")));
	}
}
