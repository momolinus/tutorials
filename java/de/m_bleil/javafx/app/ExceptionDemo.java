package de.m_bleil.javafx.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ExceptionDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button runException = new Button("execute unhandled exception");
		runException.setOnAction(e -> {
			throw new RuntimeException("unhandled");
		});

		Thread.UncaughtExceptionHandler customHandler = (t, e) -> {
			System.err.println("##################################");
			e.printStackTrace();
			System.exit(9);
		};

		CheckBox customExceptionhandler = new CheckBox("custom event handler");
		customExceptionhandler.setSelected(false);
		customExceptionhandler.selectedProperty().addListener(l -> {
			if (customExceptionhandler.isSelected()) {
				Thread.currentThread().setUncaughtExceptionHandler(customHandler);
			}
			else {
				Thread.currentThread().setUncaughtExceptionHandler(null);
			}
		});

		FlowPane root = new FlowPane(5, 5);
		root.getChildren().addAll(runException, customExceptionhandler);

		Scene scene = new Scene(root, 600, 400);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
