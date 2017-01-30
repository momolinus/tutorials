/**
 *
 */
package de.m_bleil.javafx.layout;

import org.pmw.tinylog.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * @author Marcus Bleil, www.marcusbleil.de
 */
public class StimulusView2 extends Region implements DebugLogAble {

	private ImageView imageView;

	private Pane pane;

	public StimulusView2(Image image, double width, double height) {

		imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		// imageView.setOnMouseEntered(e -> Logger.info("mouse entered in imageView"));
		// imageView.setOnMouseExited(e -> Logger.info("mouse exited from imageView"));
		Rectangle clip = new Rectangle(100, 70);
		clip.setId("clip_for_image_view");
		imageView.setClip(clip);
		imageView.setId("image_view");

		pane = new Pane(imageView);
		pane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		pane.setId("pane_with_image_view");

		pane.setOnMouseEntered(e -> Logger.info("mouse entered in image's pane container"));
		pane.setOnMouseExited(e -> Logger.info("mouse exited from image's pane container"));

		// Pane geht hier nicht, weil es die Insets nicht berücksichtigt
		StackPane pane2 = new StackPane();
		pane2.setPadding(new Insets(15));
		pane2.getChildren().add(pane);
		pane2.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

		getChildren().add(pane2);

		this.setId("stimulus_view");
	}

	@Override
	public void debugLog() {
		ImageViewDemo.logResizeableProperty(imageView);
		ImageViewDemo.logBounds(imageView);
		System.out.println();

		ImageViewDemo.logResizeableProperty(pane);
		ImageViewDemo.logBounds(pane);
		System.out.println();

		System.out.println();

		ImageViewDemo.logResizeableProperty(this);
		ImageViewDemo.logBounds(this);
	}

	public void translateClip() {
		Node clip = imageView.getClip();

		clip.relocate(clip.getLayoutX(), clip.getLayoutY() + 35);
		ImageViewDemo.logBounds(clip);
	}
}
