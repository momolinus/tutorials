package de.m_bleil.javafx.properties;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.awt.Point;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.junit.Test;

public class SimplePropertyInspectionTest {

	@Test
	public void inspect_setValue_method_for_SimpleBooleanProperty() {
		Boolean firstTrueValue = Boolean.valueOf(true);
		Boolean firstWrappedValue, secondWrappedValue;
		SimpleBooleanProperty boolProperty = new SimpleBooleanProperty();

		// note: stores no reference to trueValue, uses
		// BooleanProprety#set(trueValue.getBooleanValue()) - take a look at source code
		boolProperty.setValue(firstTrueValue);

		firstWrappedValue = boolProperty.getValue();
		// because firstTrueValue was constructed with Boolean.valueOf(true)
		assertThat(firstWrappedValue, is(sameInstance(firstTrueValue)));

		// construct a new Boolean instance
		Boolean secondTrueValue = new Boolean(true);
		boolProperty.setValue(secondTrueValue);

		// boolProperty "uses" for true the first created Boolean object with attribute true, here
		// it is trueValue
		secondWrappedValue = boolProperty.getValue();
		assertThat(secondWrappedValue, is(not(sameInstance(secondTrueValue))));
		// but
		assertThat(secondWrappedValue, is(sameInstance(firstWrappedValue)));
		assertThat(secondWrappedValue, is(sameInstance(firstTrueValue)));
	}

	@Test
	public void inspect_setValue_method_for_SimpleListProperty() {
		SimpleListProperty<String> stringListProperty = new SimpleListProperty<>();
		ObservableList<String> stringList, oldStringList;

		stringList = FXCollections.observableArrayList("item1", "item2", "item3");

		// note: stores a reference to wrappedList
		stringListProperty.set(stringList);
		ObservableList<String> wrappedStringList = stringListProperty.get();
		assertThat(wrappedStringList, is(sameInstance(stringList)));

		stringList = FXCollections.observableArrayList("chapter1", "chapter2", "chapter3");
		oldStringList = stringListProperty.get();
		assertThat(stringList, is(not(sameInstance(oldStringList))));
		assertThat(stringList, is(not(sameInstance(wrappedStringList))));
		assertThat(oldStringList, is(sameInstance(wrappedStringList)));
	}

	@Test
	public void inspect_setValue_method_forSimpleObjectProperty() {
		Point point = new Point(2, 4);
		SimpleObjectProperty<Point> pointProperty = new SimpleObjectProperty<>();

		// assumption: stores an reference to point, approved by taking a look into source code
		pointProperty.set(point);
		Point wrappedPoint = pointProperty.get();
		assertThat(wrappedPoint, is(sameInstance(point)));

		point.x = 3;
		Point wrappedPoint2 = pointProperty.get();
		assertThat(wrappedPoint2, is(sameInstance(point)));
		assertThat(wrappedPoint2, is(sameInstance(wrappedPoint)));
		assertThat(wrappedPoint.x, is(equalTo(3)));
		assertThat(wrappedPoint2.x, is(equalTo(3)));
	}
}
