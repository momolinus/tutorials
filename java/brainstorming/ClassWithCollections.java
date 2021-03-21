/**
 *
 */
package brainstorming;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/imstrat.html
 *
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class ClassWithCollections {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>(), strings2;
		List<Point> points = new ArrayList<>(), points2;

		ClassWithCollections app = new ClassWithCollections();

		app.addPoints(points);
		app.addStrings(strings);

		// darf nicht auf strings und points wirken
		app.spiegelPoinst();
		app.capitalizeStrings();

		// darf nicht auf app wirken
		strings.forEach(s -> s.toUpperCase());
		points.forEach(p -> p.translate(5, 5));

		points2 = app.getPoints();
		strings2 = app.getStrings();
		// darf nicht auf strings2 und points2 wirken
		app.spiegelPoinst();
		app.capitalizeStrings();

		// darf nicht auf app wirken
		strings2.forEach(s -> s.toUpperCase());
		points2.forEach(p -> p.translate(5, 5));
	}

	/**
	 *
	 */
	private void capitalizeStrings() {
		// TODO Auto-generated method stub

	}

	/**
	 *
	 */
	private void spiegelPoinst() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return
	 */
	private List<String> getStrings() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	private List<Point> getPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param strings
	 */
	private void addStrings(List<String> strings) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param points
	 */
	private void addPoints(List<Point> points) {
		// TODO Auto-generated method stub

	}

}
