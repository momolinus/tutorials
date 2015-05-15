/**
 * created at 14.05.2015 (16:20:09)
 */
package de.m_bleil.javafx.binding;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerPropertyBase;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Marcus Blei
 */
public class BindingDemo {

	public static void main(String[] args) {

		IntegerPropertyBase numberA = new SimpleIntegerProperty(1);
		IntegerPropertyBase numberB = new SimpleIntegerProperty(2);

		InvalidationListener invalidationListener =
			observable -> System.out.println("numberA received invalidation event");

		// method of interface Observable
		numberA.addListener(invalidationListener);

		// binding is a behavior of Properties
		numberA.bind(numberB);

		/************************************/
		/*** binding with lazy evaluation ***/
		/************************************/

		// invokes the listener method, makes binding invalid
		System.out.println("numberB.set(3);");
		numberB.set(3);

		// invokes the listener method not again, binding is still invalid
		System.out.println("numberB.set(4);");
		numberB.set(4);

		// makes binding valid, evaluation is necessary now
		System.out.println("numberA.get() = " + numberA.get());

		// invokes the listener method, shows binding was valid
		System.out.println("numberB.set(5);");
		numberB.set(5);

		System.out.println("------");

		/*****************************************************************/
		/*** binding using a ChangeListener enforces eager computation ***/
		/***
		 * compare
		 * http://docs.oracle.com/javase/8/javafx/properties-binding-tutorial/binding.htm#JFXBD107
		 ***/
		/*****************************************************************/

		// method of interface ObservableValue (extends Observable)
		numberA.addListener((observable, oldValue, newValue) -> {
			System.out.println("numberA received change event");
		});

		// invokes the both listener method, makes binding valid immediately
		System.out.println("numberB.set(6);");
		numberB.set(6);

		// invokes the both listener method again, makes binding valid immediately
		System.out.println("numberB.set(7);");
		numberB.set(7);

		// binding is valid, no listener method is invoked
		System.out.println("numberA.get() = " + numberA.get());

		System.out.println("------");

		/*****************************************************************/
		// TODO Kommentare
		System.out.println("------");

		/*****************************************************************/

		numberA.removeListener(invalidationListener);

		// invokes the both listener method, makes binding valid immediately
		System.out.println("numberB.set(8);");
		numberB.set(8);

		// invokes the both listener method again, makes binding valid immediately
		System.out.println("numberB.set(9);");
		numberB.set(9);

		// binding is valid, no listener method is invoked
		System.out.println("numberA.get() = " + numberA.get());
	}
}
