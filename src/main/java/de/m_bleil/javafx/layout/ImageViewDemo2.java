package de.m_bleil.javafx.layout;

import org.pmw.tinylog.Logger;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ImageViewDemo2 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		String imageUrl = getClass().getResource("teneriffa.jpg").toString();
		Image image = new Image(imageUrl);
		Logger.info("image w/h = {}/{}", image.getWidth(), image.getHeight());

		// image view has only CCS attributes of Node, so you can't set borders
		// see:
		// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html#imageview
		StimulusView imageView = new StimulusView(image, 300, 300);
		imageView.setId("imageView");

		Button debugPrintButton = new Button("debug log");
		debugPrintButton.relocate(400, 50);

		// TODO mit FlowPane sieht das ganz anders aus, da geht translate aber nicht relocate
		Pane root = new Pane();
		root.getChildren().addAll(imageView, debugPrintButton);

		Scene scene = new Scene(root, 800, 600);

		debugPrintButton.setOnAction(e -> {
			imageView.debugLog();
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
