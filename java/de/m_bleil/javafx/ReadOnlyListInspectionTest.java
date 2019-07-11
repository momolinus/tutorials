package de.m_bleil.javafx;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;

public class ReadOnlyListInspectionTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void show_that_default_constructor_constructs_no_list() {
		ReadOnlyListWrapper<String> errorMessages = new ReadOnlyListWrapper<>();

		expectedException.expect(NullPointerException.class);

		errorMessages.get().add("error msg1");
	}

	@Test
	public void show_that_simple_add_method_is_not_supported() {
		ReadOnlyListWrapper<String> errorMessages = new ReadOnlyListWrapper<>();

		expectedException.expect(UnsupportedOperationException.class);

		errorMessages.add("error msg2");
	}

	@Test
	public void show_valid_use_of_ReadOnlyListWrapper() {
		ReadOnlyListWrapper<String> errorMessages =
			new ReadOnlyListWrapper<>(FXCollections.observableArrayList());

		errorMessages.get().add("errormsg3");
		String msg = errorMessages.getReadOnlyProperty().get(0);

		assertThat(msg, is(equalTo("errormsg3")));
	}
}