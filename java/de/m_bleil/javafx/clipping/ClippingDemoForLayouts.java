package de.m_bleil.javafx.clipping;

import org.pmw.tinylog.Logger;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ClippingDemoForLayouts extends Application {

	@Override
	public void start(Stage primaryStage) {

		// https://www.flickr.com/photos/marcus-bleil/15956654341/in/album-72157649566590176/
		// licenses: https://creativecommons.org/licenses/by/2.0/
		Image teneriffa = new Image(getClass().getResourceAsStream("teneriffa.jpg"));

		Rectangle clip = new Rectangle(100, 75);
		//clip.setLayoutX(120);
		//clip.setLayoutY(150);
		clip.setId("clip");
		ImageView imageView = new ImageView(teneriffa);
		imageView.setClip(clip);
		imageView.setId("imageView");

		Button toggleClipButton = new Button("clip on/off");
		toggleClipButton.setOnAction(e -> {
			if (imageView.getClip() == null) {
				imageView.setClip(clip);
			}
			else {
				imageView.setClip(null);
			}

			logBounds(imageView, clip);
		});

		StackPane root = new StackPane();
		root.getChildren().addAll(imageView, toggleClipButton);

		Scene scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);

		primaryStage.setOnShown(e -> {
			logBounds(imageView);
		});

		primaryStage.show();
	}

	private void logBounds(Node... nodes) {

		for (Node node : nodes) {
			Logger.debug("{}: {}", node.getId(), node.boundsInParentProperty().get());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
