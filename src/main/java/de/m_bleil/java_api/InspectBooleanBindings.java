/**
 *
 */
package de.m_bleil.java_api;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class InspectBooleanBindings {

	static class Valve {

		private boolean open = false;

		public void close() {
			open = false;
		}

		public void open() {
			open = true;
		}

		protected boolean isOpen() {
			return open;
		}

	}

	@Test
	public void test() {
		ReadOnlyObjectWrapper<Valve> customer = new ReadOnlyObjectWrapper<>();

		BooleanBinding booleanBinding = customer.isNotNull();

		assertThat(booleanBinding.get(), is(false));
	}

	@Test
	public void test2() {
		ReadOnlyObjectWrapper<Valve> customer = new ReadOnlyObjectWrapper<>();
		Valve valve = new Valve();
		customer.set(valve);
		BooleanBinding booleanBinding = customer.isNotNull();

		assertThat(booleanBinding.get(), is(true));
		assertThat(valve.isOpen(), is(false));
	}

	@Test
	public void test3() {
		ReadOnlyObjectWrapper<Valve> customer = new ReadOnlyObjectWrapper<>();
		Valve valve = new Valve();
		customer.set(valve);
		BooleanBinding booleanBinding = customer.isNotNull();
		valve.open();

		assertThat(booleanBinding.get(), is(true));
		assertThat(valve.isOpen(), is(true));
	}
}
