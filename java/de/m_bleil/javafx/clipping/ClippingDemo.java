package de.m_bleil.javafx.clipping;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ClippingDemo extends Application {

	private ImageView imageView;
	private Pane whiteImagePane;
	private Circle redCircleNode;
	private Canvas simpleOvalCanvas;
	private Canvas complexesBluerCanvas;
	private StackPane kombi = new StackPane();
	private StackPane kombi2 = new StackPane();

	private Rectangle rec;

	@Override
	public void start(Stage primaryStage) {
		buildClippingNodes();

		Pane root = new FlowPane();

		// https://www.flickr.com/photos/marcus-bleil/15956654341/in/album-72157649566590176/
		// licenses: https://creativecommons.org/licenses/by/2.0/
		Image image = new Image(getClass().getResourceAsStream("teneriffa.jpg"));
		imageView = new ImageView(image);

		root.getChildren().addAll(imageView, buildClippingChoiceBox());

		Scene scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * 
	 */
	private void buildClippingNodes() {
		Image whiteImage = new Image(getClass().getResourceAsStream("white.jpg"));
		whiteImagePane = new Pane(new ImageView(whiteImage));
		whiteImagePane.setLayoutX(50);
		whiteImagePane.setLayoutY(50);

		redCircleNode = new Circle(100, Color.RED);
		redCircleNode.setLayoutX(250);
		redCircleNode.setLayoutY(200);

		simpleOvalCanvas = new Canvas(400, 300);
		GraphicsContext graphics = simpleOvalCanvas.getGraphicsContext2D();
		// Farbe nicht notwendig
		graphics.fillRect(0, 0, 200, 175);
		simpleOvalCanvas.setLayoutX(50);
		simpleOvalCanvas.setLayoutY(50);

		rec = new Rectangle(200, 175, new Color(0, 0, 0, 0.5));
		rec = new Rectangle(200, 175);
		rec.setLayoutX(50);
		rec.setLayoutY(50);
		// rec.setEffect(new GaussianBlur());

		buildComplexBlurCanvas();

		buildKombiniertesCanvas();
		buildKombiniertesCanvas2();
	}

	/**
	 * 
	 */
	private void buildKombiniertesCanvas() {

		Rectangle r1 = new Rectangle(200, 175, new Color(1, 1, 1, 0.5));
		// r1.setEffect(new GaussianBlur());
		Rectangle r2 = new Rectangle(100, 75);
		r2.setLayoutX(50);
		r2.setLayoutY(60);

		Pane blurPane = new Pane(r1);
		blurPane.setEffect(new GaussianBlur());
		kombi.getChildren().addAll(blurPane, r2);
		// kombi.setBlendMode(BlendMode.EXCLUSION);
		kombi.setLayoutX(50);
		kombi.setLayoutY(50);
	}

	private void buildKombiniertesCanvas2() {

		Rectangle r1 = new Rectangle(200, 175, new Color(1, 1, 1, 0.5));
		// r1.setEffect(new GaussianBlur());
		Rectangle r2 = new Rectangle(100, 75);
		r2.setLayoutX(50);
		r2.setLayoutY(60);

		kombi2.getChildren().addAll(r1, r2);
		// kombi.setBlendMode(BlendMode.EXCLUSION);
		kombi2.setLayoutX(50);
		kombi2.setLayoutY(50);
	}

	/**
	 * 
	 */
	private void buildComplexBlurCanvas() {
		complexesBluerCanvas = new Canvas(400, 300);
		complexesBluerCanvas.setLayoutX(50);
		complexesBluerCanvas.setLayoutY(50);

		GraphicsContext graphics = complexesBluerCanvas.getGraphicsContext2D();

		// hat keinen Effekt
		// graphics.setFill(new Color(0.5, 0.5, 0.5, 1));
		// dagegen schon
		// graphics.setFill(new Color(0.5, 0.5, 0.5, 0.5));
		graphics.fillRect(0, 0, 300, 200);

		complexesBluerCanvas.setEffect(new GaussianBlur());
	}

	/**
	 * @return
	 */
	private Node buildClippingChoiceBox() {
		VBox vbox = new VBox();
		ToggleGroup group = new ToggleGroup();

		RadioButton noClipping = new RadioButton("kein Clipping");
		noClipping.setSelected(true);
		noClipping.setToggleGroup(group);
		noClipping.setOnAction(e -> imageView.setClip(null));

		RadioButton whitePicture = new RadioButton("weißes Bild");
		whitePicture.setToggleGroup(group);
		whitePicture.setOnAction(e -> {
			//whiteImagePane.setBlendMode(BlendMode.MULTIPLY);
			imageView.setClip(whiteImagePane);
		});

		RadioButton redCircle = new RadioButton("roter Kreis");
		redCircle.setToggleGroup(group);
		redCircle.setOnAction(e -> imageView.setClip(redCircleNode));

		RadioButton simpleCanvas = new RadioButton("einfaches Canvas");
		simpleCanvas.setToggleGroup(group);
		simpleCanvas.setOnAction(e -> {
			imageView.setEffect(null);
			imageView.setClip(simpleOvalCanvas);
		});

		RadioButton blur = new RadioButton("unschärfe");
		blur.setToggleGroup(group);
		blur.setOnAction(e -> {
			imageView.setEffect(new GaussianBlur());
			imageView.setClip(rec);
		});

		RadioButton k = new RadioButton("kombi");
		k.setToggleGroup(group);
		k.setOnAction(e -> {

			imageView.setClip(kombi);
		});

		RadioButton k2 = new RadioButton("kombi 2");
		k2.setToggleGroup(group);
		k2.setOnAction(e -> {

			imageView.setClip(kombi2);
		});

		vbox.getChildren().addAll(noClipping, whitePicture, redCircle, simpleCanvas, blur, k, k2);

		return vbox;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
