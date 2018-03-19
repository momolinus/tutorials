/**
 *
 */
package de.m_bleil.javafx.layout;

import org.pmw.tinylog.Logger;

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
public class StimulusView extends Region implements DebugLogAble {

	private Border border;

	private Border border2;

	private Border border3;

	private ImageView imageView;

	private Pane pane;

	private FlowPane flowPane;

	/**
	 * @param image
	 * @param i
	 * @param j
	 */
	public StimulusView(Image image, double width, double height) {

		BorderStrokeStyle outsideStyle = new BorderStrokeStyle(StrokeType.OUTSIDE,
			StrokeLineJoin.ROUND, StrokeLineCap.SQUARE, 0, 0, null);

		border = new Border(new BorderStroke(Color.GOLD, outsideStyle, null, new BorderWidths(5)));
		border2 =
			new Border(new BorderStroke(Color.MAGENTA, outsideStyle, null, new BorderWidths(5)));
		border3 = new Border(new BorderStroke(Color.CYAN, outsideStyle, null, new BorderWidths(5)));

		double borderSize = border3.getOutsets().getBottom();

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
		pane.setBorder(border);
		pane.setId("pane_with_image_view");
		pane.setOnMouseEntered(e -> Logger.info("mouse entered in image's pane container"));
		pane.setOnMouseExited(e -> Logger.info("mouse exited from image's pane container"));

		flowPane = new FlowPane(pane);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setBorder(border2);
		flowPane.setPrefSize(	imageView.getFitWidth() + 2 * borderSize,
								imageView.getFitHeight() + 2 * borderSize);
		flowPane.setLayoutX(borderSize);
		flowPane.setLayoutY(borderSize);
		flowPane.setId("flow_pane_with_pane");

		getChildren().add(flowPane);

		this.setPrefSize(	flowPane.getPrefWidth() + 2 * borderSize,
							flowPane.getPrefHeight() + 2 * borderSize);
		this.setBorder(border3);
		this.setLayoutX(2 * borderSize);
		this.setLayoutY(2 * borderSize);

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

		ImageViewDemo.logResizeableProperty(flowPane);
		ImageViewDemo.logBounds(flowPane);
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
