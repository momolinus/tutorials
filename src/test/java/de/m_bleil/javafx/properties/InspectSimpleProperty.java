package de.m_bleil.javafx.properties;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import javafx.beans.property.SimpleBooleanProperty;

import org.junit.Test;

public class InspectSimpleProperty {

	@Test
	public void inspect_setValue_method_for_SimpleBooleanProperty() {
		Boolean trueValue = Boolean.valueOf(true);
		Boolean wrappedValue, secondWrappedValue;
		SimpleBooleanProperty boolProperty = new SimpleBooleanProperty();

		// note: stores no reference to trueValue, uses
		// BooleanProprety#set(trueValue.getBooleanValue()) - take a look at source code
		boolProperty.setValue(trueValue);

		wrappedValue = boolProperty.getValue();
		assertThat(wrappedValue, is(sameInstance(trueValue)));

		// construct a new Boolean instance
		trueValue = new Boolean(true);
		boolProperty.setValue(trueValue);

		secondWrappedValue = boolProperty.getValue();
		assertThat(secondWrappedValue, is(not(sameInstance(trueValue))));
		assertThat(secondWrappedValue, is(sameInstance(wrappedValue)));
	}
}
