package de.m_bleil.javafx.clipping;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ClippingDemo2 extends Application {

	@Override
	public void start(Stage primaryStage) {

		StackPane root = new StackPane();

		// https://www.flickr.com/photos/marcus-bleil/15956654341/in/album-72157649566590176/
		// licenses: https://creativecommons.org/licenses/by/2.0/
		Image teneriffa = new Image(getClass().getResourceAsStream("teneriffa.jpg"));

		Rectangle r1 = new Rectangle(100, 75);
		r1.setLayoutX(120);
		r1.setLayoutY(150);
		ImageView imageView = new ImageView(teneriffa);
		imageView.setClip(r1);

		ImageView blurImV = new ImageView(teneriffa);
		Rectangle r = new Rectangle(300, 200);
		r.setLayoutX(20);
		r.setLayoutY(100);
		blurImV.setClip(r);
		blurImV.setEffect(new GaussianBlur());

		root.getChildren().addAll(blurImV, imageView);

		Scene scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
