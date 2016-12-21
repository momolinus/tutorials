package de.m_bleil.javafx.properties;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class InspectChangeListener {

	private StringProperty projectName;
	private AtomicInteger counter;

	@Before
	public void setup() {
		projectName = new SimpleStringProperty("Project Java tutorial");
		counter = new AtomicInteger();

		projectName.addListener((ObservableValue<? extends String> name, String oldName,
			String newName) -> {

			counter.incrementAndGet();

			if (newName == null || newName.length() == 0) {
				projectName.set(oldName);
			}
		});
	}

	@Test
	public void test_change_listener() {

		projectName.set(null);

		assertThat(counter.get(), is(equalTo(2)));
	}

	@Test
	public void test_change_listener_1() {

		projectName.set("");

		assertThat(counter.get(), is(equalTo(2)));
	}

	@Test
	public void test_change_listener_2() {

		projectName.set("test");

		assertThat(counter.get(), is(equalTo(1)));
	}
}
