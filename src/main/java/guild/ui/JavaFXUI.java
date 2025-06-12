package guild.ui;

import guild.main.GuildAppRunner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class JavaFXUI extends Application {

    private TextArea textArea;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load and scale background image to 640x960
        Image backgroundImage = new Image(new FileInputStream("src/main/resources/Background.png"));
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(640);
        imageView.setFitHeight(960);
        imageView.setPreserveRatio(false);

        // Create and style the output TextArea
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setStyle(
                "-fx-control-inner-background: black;" +       // Text input area background
                        "-fx-background-color: black;" +               // Outer box background
                        "-fx-text-fill: lightgreen;" +                 // Text color
                        "-fx-font-size: 13px;" +
                        "-fx-font-family: 'Consolas';" +
                        "-fx-border-color: transparent;" +             // Removes borders
                        "-fx-background-insets: 0;" +
                        "-fx-background-radius: 0;"
        );

        // Manually scaled positioning and sizing (0.625 of original)
        textArea.setLayoutX(76);     // 82 * 0.625
        textArea.setLayoutY(226);    // 250 * 0.625
        textArea.setPrefWidth(487);  // 860 * 0.625
        textArea.setPrefHeight(527); // 860 * 0.625

        Pane root = new Pane();
        root.getChildren().addAll(imageView, textArea);

        Scene scene = new Scene(root, 640, 960);
        primaryStage.setTitle("Star Wars Bounty Guild Log");
        primaryStage.setScene(scene);
        primaryStage.show();

        redirectSystemOutputs();

        // Run guild logic in background to keep UI responsive
        new Thread(() -> new GuildAppRunner().run()).start();
    }

    private void redirectSystemOutputs() {
        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                Platform.runLater(() -> textArea.appendText(String.valueOf((char) b)));
            }
        });
        System.setOut(ps);
        System.setErr(ps);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
