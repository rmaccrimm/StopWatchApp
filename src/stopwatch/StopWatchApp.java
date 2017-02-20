package stopwatch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author rmaccrimmon
 */
public class StopWatchApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setAlignment(Pos.CENTER);
        
        Label timerLabel = new Label("0");
        Label[] labels = new Label[20];
        for(int i = 0; i < 20; i++) {
            labels[i] = new Label("test");
        }
        
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button resetButton = new Button("Reset");
        Button lapButton = new Button("Lap");
        
        startButton.setMaxWidth(Double.MAX_VALUE);
        stopButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setMaxWidth(Double.MAX_VALUE);
        lapButton.setMaxWidth(Double.MAX_VALUE);
        
        timerLabel.setFont(Font.font("Liberation Sans", 32));
        GridPane.setHalignment(timerLabel, HPos.CENTER);
        
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(60, 120);
        scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
        
        VBox vb = new VBox();
        vb.setPadding(new Insets(5, 10, 5, 10));
        
        scroll.setContent(vb);
        
        grid.add(timerLabel, 0, 0, 3, 1);
        grid.add(startButton, 0, 1);
        grid.add(stopButton, 1, 1);
        grid.add(resetButton, 2, 1);
        grid.add(lapButton, 0, 2, 3, 1);
        grid.setVgap(10);
        grid.setHgap(10);

        StopWatchTimer timer = new StopWatchTimer(timerLabel, 0);
        
        startButton.setOnAction((ActionEvent evt) -> {
            timer.start();
        });
        
        stopButton.setOnAction((ActionEvent evt) -> {
            timer.stop();
        });
        
        resetButton.setOnAction((ActionEvent evt) -> {
            timer.reset();
        });
        
        lapButton.setOnAction((ActionEvent evt) -> {
            vb.getChildren().add(new Label(
                    StopWatchTimer.formatTime(timer.getLap(), 3)));
        });
        
        border.setCenter(grid);
        border.setRight(scroll);
        
        Scene scene = new Scene(border, 450, 250);
        primaryStage.setTitle("Stop Watch");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
