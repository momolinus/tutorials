package de.m_bleil.javafx.layout;

import org.pmw.tinylog.Logger;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageViewDemo extends Application {

	public static void logBounds(Node node) {
		Logger.info("{} layout x/y = {}", node.getId(), node.layoutXProperty().get(),
					node.layoutYProperty().get());
		Logger.info("{} layout bounds {}", node.getId(), node.layoutBoundsProperty().get());
		Logger.info("{} bounds local {}", node.getId(), node.boundsInLocalProperty().get());
		Logger.info("{} bounds parent {}", node.getId(), node.boundsInParentProperty().get());
	}

	public static void logResizeableProperty(Node node) {
		Logger.info("{} resizeable property is {}", node.getId(), node.isResizable());
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Border border = new Border(new BorderStroke(Color.GOLD, BorderStrokeStyle.DOTTED,
			new CornerRadii(5), new BorderWidths(10)));

		String imageUrl = getClass().getResource("teneriffa.jpg").toString();
		Image image = new Image(imageUrl);
		Logger.info("image w/h = {}/{}", image.getWidth(), image.getHeight());

		// image view has only CCS attributes of Node, so you can't set borders
		// see:
		// https://docs.oracle.com/javase/8/javafx/api/javafx/scene/doc-files/cssref.html#imageview
		ImageView imageView = new ImageView(image);
		imageView.setId("imageView");

		Button debugPrintButton = new Button("debug log");
		debugPrintButton.relocate(400, 50);

		Pane imageViewContainer = new Pane(imageView);
		imageViewContainer.setBorder(border);
		imageViewContainer.relocate(10, 10);
		imageViewContainer.setTranslateX(10);
		imageViewContainer.setTranslateY(10);
		imageViewContainer.setId("imageViewContainer");

		// TODO mit FlowPane sieht das ganz anders aus, da geht translate aber nicht relocate
		Pane root = new Pane();
		root.getChildren().addAll(imageViewContainer, debugPrintButton);

		Scene scene = new Scene(root, 800, 600);

		debugPrintButton.setOnAction(e -> {
			logResizeableProperty(imageView);
			logResizeableProperty(imageViewContainer);

			System.out.println();

			logBounds(imageView);

			System.out.println();

			logBounds(imageViewContainer);
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
