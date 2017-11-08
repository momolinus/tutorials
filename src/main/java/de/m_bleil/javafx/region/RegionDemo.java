/**
 *
 */
package de.m_bleil.javafx.region;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class RegionDemo extends Region {

	private Rectangle rectangle;

	public RegionDemo() {
		setStyle("-fx-background-color: azure;");
		setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		setPrefSize(400, 300);
		setMaxSize(800, 600);

		rectangle = new Rectangle(10, 10, 70, 50);
		rectangle.setFill(Color.web("#FFBF00"));
		getChildren().add(rectangle);
	}

	@Override
	protected double computePrefWidth(double height) {
		double width = height * 3.0 / 2.0;

		if (width > 0) {
			return width;
		}
		else {
			return 400;
		}
	}

	@Override
	protected double computeMinWidth(double height) {
		// TODO Auto-generated method stub
		return 200;
	}

	@Override
	protected double computeMinHeight(double width) {
		// TODO Auto-generated method stub
		return 200 * 0.66;
	}

	@Override
	protected double computePrefHeight(double width) {
		double height = width * 2.0 / 3.0;

		if (height > 0) {
			return height;
		}
		else {
			return 300;
		}
	}

	@Override
	protected void layoutChildren() {
		rectangle.relocate(getLayoutBounds().getMaxX() - 10 - 70, 10);
	}
}
