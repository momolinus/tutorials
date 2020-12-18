package de.m_bleil.javafx.clipping;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.Effect;
import javafx.scene.effect.FloatMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class LensEffect extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        root.setPrefSize(370, 620);

        Rectangle rect = new Rectangle(350, 600);
        rect.setFill(null);
        rect.setStroke(Color.RED);
        rect.setStrokeWidth(4);
        root.getChildren().add(rect);

        ImageView view = new ImageView(new Image(getClass().getResource("buchstaben.jpg").toString()));
        view.setEffect(createEffect(350, 600));
        root.getChildren().add(view);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private Effect createEffect(int width, int height) {
        FloatMap floatMap = new FloatMap(width, height);

        for (int x = 0; x < width; x++) {
            float u = (float) Math.sin(Math.PI * x / width * 2.0) * 0.05f;
            for (int y = 0; y < height; y++) {
                float v = (float) Math.sin(Math.PI * y / height * 2.0) * 0.05f;
                floatMap.setSamples(x, y, u, 0.0f);
            }
        }

        DisplacementMap displacementMap = new DisplacementMap(floatMap);

        return displacementMap;
    }

    public static void main(String... args) {
        launch(args);
    }
}
