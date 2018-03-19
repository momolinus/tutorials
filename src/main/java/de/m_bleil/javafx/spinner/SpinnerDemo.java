package de.m_bleil.javafx.spinner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SpinnerDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		Pane root = new FlowPane();

		Spinner<Integer> spinnerMaster = new Spinner<>();
		spinnerMaster.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));

		Spinner<Integer> spinner = new Spinner<>();

		spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
		// spinner.getValueFactory().valueProperty().bind(spinnerMaster.valueProperty());
		spinnerMaster.valueProperty().addListener(l -> {
			Integer masterValue = spinnerMaster.valueProperty().get();
			spinner.getValueFactory().setValue(masterValue.intValue() * 2);
		});

		TextField textField = new TextField();
		textField.textProperty().bind(spinner.valueProperty().asString());

		root.getChildren().addAll(spinnerMaster, spinner, textField);

		Scene scene = new Scene(root, 600, 480);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
