package de.m_bleil.javafx.binding;

import java.util.concurrent.atomic.AtomicInteger;

import org.pmw.tinylog.Logger;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BindingWithGuiDemo extends Application {

	static class ViewModel implements EventHandler<ActionEvent> {

		private StringProperty input = new SimpleStringProperty();

		private final AtomicInteger counter = new AtomicInteger(0);

		public ViewModel() {

			input.addListener((ObservableValue<? extends String> inputValue, String oldInput,
				String newInput) -> {

				Logger.info("Model: {} -> {}, no. {}", oldInput, newInput,
							counter.incrementAndGet());

				if (newInput == null || newInput.length() == 0) {

					reset("reset");
				}
			});
		}

		/**
		 * @param string
		 */
		public void reset(String string) {
			KeyValue keyValue = new KeyValue(input, string);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
			Timeline timeline = new Timeline(keyFrame);

			timeline.play();
		}

		public final String getInput() {
			return this.inputProperty().get();
		}

		public final StringProperty inputProperty() {
			return this.input;
		}

		public final void setInput(final String input) {
			this.inputProperty().set(input);
		}

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Button setBreakButton = new Button("set model to x");

		TextField input = new TextField();
		input.setMinWidth(100);

		ViewModel model = new ViewModel();

		// geht nicht
		input.textProperty().bindBidirectional(model.inputProperty());

		// nur das geht nicht
		// input.textProperty().bind(model.inputProperty());

		// geht auch nicht
		/*
		 * input.textProperty().addListener(l -> { model.inputProperty().set(input.getText()); });
		 * input.textProperty().bind(model.inputProperty());
		 */

		input.textProperty().addListener((ObservableValue<? extends String> inputValue,
			String oldInput, String newInput) -> {
			Logger.info("TextFiel: {} -> {}", oldInput, newInput);
			model.inputProperty().set(newInput);
		});
		model.inputProperty().addListener(l -> {
			input.textProperty().set(model.inputProperty().get());
		});

		setBreakButton.setOnAction(e -> model.reset("vom Button"));

		Label label = new Label();
		label.textProperty().bind(model.inputProperty());

		FlowPane root = new FlowPane();
		root.getChildren().addAll(input, setBreakButton, label);

		Scene scene = new Scene(root, 400, 300);

		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
