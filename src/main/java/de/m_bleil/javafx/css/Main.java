/**
 * created at 26.04.2017
 */
package de.m_bleil.javafx.css;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Pane group = new Pane();

		Pane button = new Pane();
		button.setLayoutX(50);
		button.setLayoutY(50);
		button.setPrefSize(200, 70);

		button.setStyle("-fx-border-radius: 20px / 10px;");
		button.setStyle("-fx-border-width: 10px;");
		button.setStyle("-fx-border-color: red;");
		button.setStyle("-fx-border-style: solid;");

		group.getChildren().add(button);

		Scene scene = new Scene(group, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}