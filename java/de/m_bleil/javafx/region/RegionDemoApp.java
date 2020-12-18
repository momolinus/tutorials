package de.m_bleil.javafx.region;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegionDemoApp extends Application {

	@Override
	public void start(Stage primaryStage) {

		AnchorPane root = new AnchorPane();
		root.setPrefSize(800, 600);
		root.setStyle("-fx-background-color: lightsteelblue;");

		RegionDemo regionDemo = new RegionDemo();
		root.getChildren().add(regionDemo);

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
