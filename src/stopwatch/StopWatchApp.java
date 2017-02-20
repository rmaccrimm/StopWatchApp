package stopwatch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author rmaccrimmon
 */
public class StopWatchApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        GridPane mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(25, 25, 25, 25));
        mainGrid.setAlignment(Pos.CENTER);
        
        Label timerLabel = new Label("0");
        timerLabel.setFont(Font.font("Liberation Sans", 32));
        GridPane.setHalignment(timerLabel, HPos.CENTER);
        mainGrid.add(timerLabel, 0, 0, 2, 1);
        
        Button startButton = new Button("Start");
        mainGrid.add(startButton, 0, 1);
        Button stopButton = new Button("Stop");
        mainGrid.add(stopButton, 1, 1);
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);

        StopWatchTimer timer = new StopWatchTimer(timerLabel, 0);
        
        startButton.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            public void handle(ActionEvent actionevent) {
                timer.start();
            }
        });
        
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
        
            @Override
            public void handle(ActionEvent actionevent) {
                timer.stop();
            }
        });
        
        Scene scene = new Scene(mainGrid, 300, 250);
        primaryStage.setTitle("Timer");
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
