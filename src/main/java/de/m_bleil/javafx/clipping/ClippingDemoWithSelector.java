package de.m_bleil.javafx.clipping;

import org.pmw.tinylog.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class ClippingDemoWithSelector extends Application {

	private static final String FIRST_FILE = "buchstaben.jpg";

	public static void main(String[] args) {
		launch(args);
	}

	private ColorAdjust colorEffect;

	private ImageView imageView;
	private ImageView imageViewBlur;

	private GaussianBlur kombiniert;

	private StackPane stimulusPane;

	private RadioButton circle;

	@Override
	public void start(Stage primaryStage) {

		initEffects();

		BorderPane root = new BorderPane();

		// https://www.flickr.com/photos/marcus-bleil/15956654341/in/album-72157649566590176/
		// licenses: https://creativecommons.org/licenses/by/2.0/
		Image image = new Image(getClass().getResourceAsStream(FIRST_FILE));

		imageViewBlur = new ImageView(image);
		imageViewBlur.setEffect(new GaussianBlur(20));

		imageView = new ImageView(image);
		stimulusPane = new StackPane(imageView);
		Pane imageViewContainer = new Pane(stimulusPane);
		imageViewContainer.setBorder(new Border(
			new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null)));

		imageViewContainer.setOnMouseMoved(e -> {
			if (imageView.getClip() != null) {
				double x = e.getX();
				double y = e.getY();

				Node clip = imageView.getClip();
				clip.setLayoutX(x - clip.getBoundsInParent().getWidth());
				clip.setLayoutY(y - clip.getBoundsInParent().getHeight());
			}
		});
		imageViewContainer.setCursor(Cursor.NONE);

		HBox control =
			new HBox(buildClippingChoiceBox(), buildEffectChoiceBox(), buildFileSelector());
		control.setPadding(new Insets(5, 0, 20, 0));
		root.setTop(control);
		root.setCenter(imageViewContainer);

		Scene scene = new Scene(root, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @return
	 */
	private Node buildFileSelector() {
		ComboBox<String> fileSelector = new ComboBox<>();
		ObservableList<String> files =
			FXCollections.observableArrayList("target2 (5).jpg", "teneriffa.jpg", "buchstaben.jpg");

		fileSelector.setItems(files);
		fileSelector.getSelectionModel().select(FIRST_FILE);

		fileSelector.getSelectionModel().selectedItemProperty().addListener(l -> {
			String fileName = fileSelector.getSelectionModel().getSelectedItem();
			Image image = new Image(getClass().getResourceAsStream(fileName));

			imageView.setImage(image);
			imageViewBlur.setImage(image);

		});

		return fileSelector;
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

		RadioButton squareSmall = new RadioButton("Quadrat klein");
		squareSmall.setToggleGroup(group);
		squareSmall.setOnAction(e -> {
			imageView.setClip(new Rectangle(50, 50));
		});

		RadioButton squareMedium = new RadioButton("Quadrat mittel");
		squareMedium.setToggleGroup(group);
		squareMedium.setOnAction(e -> {
			imageView.setClip(new Rectangle(100, 100));
		});

		RadioButton squareLarge = new RadioButton("Quadrat groß");
		squareLarge.setToggleGroup(group);
		squareLarge.setOnAction(e -> {
			imageView.setClip(new Rectangle(200, 200));
		});

		circle = new RadioButton("Kreis mit Maus im Mittelpunkt");
		circle.setToggleGroup(group);
		circle.setOnAction(e -> {
			Circle circleClip = new Circle(100, 100, 50);
			imageView.setClip(circleClip);
		});

		RadioButton circle2 = new RadioButton("Kreis (kleiner) mit Maus am Rand");
		circle2.setToggleGroup(group);
		circle2.setOnAction(e -> {
			Circle circleClip = new Circle(50, 50, 50);
			imageView.setClip(circleClip);
		});

		RadioButton path = new RadioButton("\"freie\" Form");
		path.setToggleGroup(group);
		path.setOnAction(e -> {
			imageView.setClip(buildPath());
		});

		vbox.getChildren().addAll(	noClipping, squareSmall, squareMedium, squareLarge, circle,
									circle2, path);

		return vbox;
	}

	private Node buildEffectChoiceBox() {
		VBox vbox = new VBox();
		ToggleGroup group = new ToggleGroup();

		RadioButton noEffect = new RadioButton("kein Effekt");
		noEffect.setToggleGroup(group);
		noEffect.setSelected(true);
		noEffect.setOnAction(e -> {
			imageView.setEffect(null);
		});

		RadioButton bluer1 = new RadioButton("Unschärfe (standard)");
		bluer1.setToggleGroup(group);
		bluer1.setOnAction(e -> {
			imageView.setEffect(new GaussianBlur());
		});

		RadioButton bluer2 = new RadioButton("leichte Unschärfe");
		bluer2.setToggleGroup(group);
		bluer2.setOnAction(e -> {
			imageView.setEffect(new GaussianBlur(5.0));
		});

		RadioButton farbe = new RadioButton("Farbveränderung");
		farbe.setToggleGroup(group);
		farbe.setOnAction(e -> {
			imageView.setEffect(colorEffect);
		});

		RadioButton komb = new RadioButton("eine Kombination");
		komb.setToggleGroup(group);
		komb.setOnAction(e -> {
			imageView.setEffect(kombiniert);
		});

		RadioButton special = new RadioButton("\"Spezial\"-Effekt");
		special.setToggleGroup(group);
		special.selectedProperty().addListener((v, oldV, newV) -> {

			if (special.isSelected()) {
				imageView.setEffect(null);
				stimulusPane.getChildren().add(imageViewBlur);
				imageView.toFront();
			}
			else {
				stimulusPane.getChildren().remove(imageViewBlur);
				Logger.debug("blur image removed");
			}
		});

		RadioButton special2 = new RadioButton("\"Spezial\"-Effekt (2)");
		special2.setToggleGroup(group);
		special2.selectedProperty().addListener((v, oldV, newV) -> {

			if (special2.isSelected()) {
				WritableImage noiseImage = new WritableImage((int) imageView.getImage().getWidth(),
					(int) imageView.getImage().getHeight());

				PixelWriter writer = noiseImage.getPixelWriter();

				for (int x = 0; x < noiseImage.getWidth() - 2; x += 2) {
					for (int y = 0; y < noiseImage.getHeight() - 2; y += 2) {
						double colorV = Math.random();
						writer.setColor(x, y, new Color(colorV, colorV, colorV,
							Math.random() * 0.5 + 0.5));
						writer.setColor(x, y + 1, new Color(colorV, colorV, colorV,
							Math.random() * 0.5 + 0.5));
						writer.setColor(x + 1, y, new Color(Math.random(), Math.random(),
							Math.random(), Math.random() * 0.5 + 0.5));
						// writer.setColor(x + 1, y + 1,
						// new Color(Math.random(), Math.random(), Math.random(), 1));
					}
				}

				ImageInput noiseInput = new ImageInput(noiseImage);
				Blend blend = new Blend(BlendMode.SRC_ATOP);
				blend.setBottomInput(new GaussianBlur(2.0));
				blend.setTopInput(noiseInput);

				imageView.setEffect(blend);
			}
			else {

			}
		});

		vbox.getChildren().addAll(noEffect, bluer1, bluer2, farbe, komb, special, special2);

		return vbox;
	}

	/**
	 * @return
	 */
	private Node buildPath() {
		Circle lupe = new Circle(75, 75, 75);
		Rectangle griff = new Rectangle(40, 100);
		griff.setRotate(135);
		griff.setX(110);
		griff.setY(110);
		return Shape.union(lupe, griff);
	}

	private void initEffects() {
		colorEffect = new ColorAdjust();
		colorEffect.setContrast(-0.8);
		colorEffect.setSaturation(-0.8);

		ColorAdjust colorEffect2 = new ColorAdjust();
		colorEffect2.setContrast(-0.8);
		colorEffect2.setSaturation(-0.8);

		kombiniert = new GaussianBlur();
		kombiniert.setInput(colorEffect2);

	}
}
