package stopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author rmaccrimmon
 */
public class StopWatchTimer {
    
    private final Timeline timer;
    private final Label listener;
    private final int decimals;
    private int time;
    private Boolean isRunning;
    
    public StopWatchTimer(Label timerLabel, int decimalPlaces) {
        listener = timerLabel;
        decimals = decimalPlaces;
        
        timer = new Timeline(
                new KeyFrame(Duration.ZERO, actionEvent -> {
                    listener.setText(StopWatchTimer.formatTime(
                            time++, decimals));
                }), 
                new KeyFrame(Duration.millis(1))
        );
        timer.setCycleCount(Animation.INDEFINITE);
    }

        /*
        Runnable updateTime = () -> {
            listener.setText(StopWatchTimer.formatTime(time++, decimals));
        };
        
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Platform.runLater(updateTime);
            }
        };
        timer =  new Timer(1, taskPerformer);
    }*/
    
        /**
         * Start the timer
         */
    public void start() {
        timer.play();
        isRunning = true;
    }
    
    /**
     *  Stop the timer
     */
    public void stop() {
        timer.stop();
        isRunning = false;
    }
    
    /**
     * Reset the timer to 0
     */
    public void reset() {
        if (!isRunning) {
            time = 0;
            listener.setText(StopWatchTimer.formatTime(time, decimals));
        }
    }
    
    /**
     * @param time - the current time in milliseconds
     * @return time in hh:mm:ss format
     */
    public static String formatTime(int time, int decimals) {
        int seconds = time/1000%60;
        int ms = time%1000;
        return String.format("%d.", seconds) + String.format("%03d", ms);
    }
    
}
