package de.m_bleil.threads;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ThreadDemo extends Application {

	@Override
	public void start(Stage primaryStage) {
		FlowPane root = new FlowPane();

		Scene scene = new Scene(root, 800, 600);
		Button button = new Button("Exception");

		button.setOnAction(e -> {
			Integer.parseInt("xyz");
			// printThreads();
		});

		Button button2 = new Button("threads");
		button2.setOnAction(e -> printThreads());

		root.getChildren().addAll(button, button2);

		primaryStage.setScene(scene);

		primaryStage.show();

		printThreads();

	}

	public static void main(String[] args) {
		launch(args);

		Thread[] threads = new Thread[100];

		int threadsCount = Thread.enumerate(threads);

		System.out.println(threadsCount + "," + threads.length);

		printThreads();
	}

	public static void printThreads() {
		Thread[] threads = new Thread[100];
		int threadCount = Thread.enumerate(threads);

		for (int i = 0; i < threadCount; i++) {
			System.out.println(threads[i].getName() + ", id= " + threads[i].getId() + ", damon: "
				+ threads[i].isDaemon() + ", group: " + threads[i].getThreadGroup().getName() + ", ex handler "
				+ threads[i].getUncaughtExceptionHandler());
		}
		System.out.println();
	}
}
