package de.m_bleil.javafx.layout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StackAndFlowPaneDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		StackPane defaultStackPane = new StackPane(
			new Label("default StackPane\nlayout set with \n#relocate and #setPrefSize"));
		defaultStackPane.relocate(10, 10);
		defaultStackPane.setPrefSize(200, 120);
		defaultStackPane.setStyle("-fx-background-color: blanchedalmond");

		FlowPane defaultFlowPane = new FlowPane(
			new Label("default FlowPane\nlayout set with \n#relocate and #setPrefSize"));
		defaultFlowPane.relocate(10, 140);
		defaultFlowPane.setPrefSize(200, 120);
		defaultFlowPane.setStyle("-fx-background-color: blanchedalmond");

		FlowPane flowPaneWithComputedSize =
			new FlowPane(new Label("FlowPane with prefSize = USE_COMPUTED_SIZE"));
		flowPaneWithComputedSize.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		flowPaneWithComputedSize.relocate(10, 280);
		// flowPaneWithComputedSize.setPrefSize(200, 120);
		flowPaneWithComputedSize.setStyle("-fx-background-color: blanchedalmond");

		Pane root = new Pane();
		root.getChildren().addAll(defaultStackPane, defaultFlowPane, flowPaneWithComputedSize);

		Scene scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
