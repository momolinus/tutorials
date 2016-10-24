package de.m_bleil.threads;

import org.pmw.tinylog.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ThreadDemo extends Application {

    public static void main(String[] args) {

	Logger.info("*** main before launching application ***");
	ThreadToolBox.printAllThreads(Thread.currentThread());

	Logger.info("*** next applications will be launched ***");
	launch(args);

	Logger.info("*** main after launching application ***");
	ThreadToolBox.printAllThreads(Thread.currentThread());

    }

    @Override
    public void start(Stage primaryStage) {
	FlowPane root = new FlowPane();

	Scene scene = new Scene(root, 800, 600);
	Button button = new Button("Exception");

	button.setOnAction(e -> {
	    Integer.parseInt("xyz");

	    // TODO wird nicht erreicht
	    Logger.info("*** after Excpetion ***");
	    ThreadToolBox.printAllThreads(Thread.currentThread());
	});

	Button button2 = new Button("threads");
	button2.setOnAction(e -> {
	    Logger.info("*** print threads ***");
	    ThreadToolBox.printAllThreads(Thread.currentThread());
	});

	root.getChildren().addAll(button, button2);

	primaryStage.setScene(scene);

	primaryStage.show();

	Logger.info("*** applications stage shown ***");
	ThreadToolBox.printAllThreads(Thread.currentThread());
    }
}
