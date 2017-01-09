package de.m_bleil.javafx.binding;

import java.util.concurrent.atomic.AtomicInteger;

import org.pmw.tinylog.Logger;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class BindingWithGuiDemo2 extends Application {

	static class ViewModel {

		private StringProperty input = new SimpleStringProperty();

		private final AtomicInteger counter = new AtomicInteger(0);

		public ViewModel() {

			input.addListener((ObservableValue<? extends String> inputValue, String oldInput,
				String newInput) -> {

				Logger.info("old = {}, new = {}, call no. {}", oldInput, newInput,
							counter.incrementAndGet());

				if (newInput == null || newInput.length() == 0) {
					// this.inputProperty().set("x");
					reset("reset");
				}
			});
		}

		/**
		 * @param string
		 */
		public void reset(String string) {
			input.set(string);
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

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Button setBreakButton = new Button("set model to x");

		TextField input = new TextField();
		input.setMinWidth(100);
		input.textProperty().addListener(l -> {
			Logger.info("input = {}", input.textProperty().get());
		});

		ViewModel model = new ViewModel();
		input.textProperty().bindBidirectional(model.inputProperty());
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
