package de.m_bleil.javafx.dpi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ShowDpi extends Application {

	@Override
	public void start(Stage primaryStage) {
		double dpi = Screen.getPrimary().getDpi();

		Label dpiLabel = new Label("Screen " + dpi + " dpi");
		dpiLabel.setLayoutX(10);
		dpiLabel.setLayoutY(10);

		double _10cmInInch = 3.937007874016;

		double pixel = _10cmInInch * dpi;
		Label cmLable = new Label("10 cm benötigen " + String.format("%.1f", pixel) + " Pixel");
		cmLable.setLayoutX(10);
		cmLable.setLayoutY(40);

		Rectangle rectangle = new Rectangle(pixel, 5, Color.BURLYWOOD);
		rectangle.setLayoutX(10);
		rectangle.setLayoutY(60);

		Pane root = new Pane();
		root.getChildren().addAll(dpiLabel, cmLable, rectangle);

		Scene scene = new Scene(root, 600, 480);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
