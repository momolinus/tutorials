/*
 * commented and created at 22.12.2016
 */
package de.m_bleil.javafx.properties;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * The Class ChangeListenerInspectionTest demonstrates the effect of reset value to old value using
 * a {@linkplain ChangeListener}.
 *
 * @author Marcus Bleil, www.m-bleil.de
 */
public class ChangeListenerInspectionTest {

	private StringProperty projectName;
	private AtomicInteger counterChangeListener;
	private AtomicInteger counterInvalidationListener;

	@Before
	public void setup() {
		projectName = new SimpleStringProperty("Project Java tutorial");
		counterChangeListener = new AtomicInteger();
		counterInvalidationListener = new AtomicInteger();

		projectName.addListener((ObservableValue<? extends String> name, String oldName,
			String newName) -> {

			counterChangeListener.incrementAndGet();

			if (newName == null || newName.length() == 0) {
				projectName.set(oldName);
			}
		});

		projectName.addListener((Observable o) -> {
			counterInvalidationListener.incrementAndGet();
		});
	}

	@Test
	public void test_change_listener() {

		projectName.set(null);

		assertThat(counterChangeListener.get(), is(equalTo(2)));
		assertThat(counterInvalidationListener.get(), is(equalTo(2)));
	}

	@Test
	public void test_change_listener_1() {

		projectName.set("");

		assertThat(counterChangeListener.get(), is(equalTo(2)));
		assertThat(counterInvalidationListener.get(), is(equalTo(2)));
	}

	@Test
	public void test_change_listener_2() {

		projectName.set("test");

		assertThat(counterChangeListener.get(), is(equalTo(1)));
		assertThat(counterInvalidationListener.get(), is(equalTo(1)));
	}

	@Test
	public void test_change_listener_3() {

		projectName.set("test");
		projectName.set("test");

		assertThat(counterChangeListener.get(), is(equalTo(1)));
		assertThat(counterInvalidationListener.get(), is(equalTo(1)));
	}
}
