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
    private int prevTime;
    private int lapTime;
    private Boolean isRunning;
    
    public StopWatchTimer(Label timerLabel, int decimalPlaces) {
        listener = timerLabel;
        decimals = decimalPlaces;
        time = 0;
        prevTime = 0;
        
        timer = new Timeline(
                new KeyFrame(Duration.ZERO, actionEvent -> {
                    listener.setText(StopWatchTimer.formatTime(
                            time++, decimals));
                }), 
                new KeyFrame(Duration.millis(1))
        );
        timer.setCycleCount(Animation.INDEFINITE);
        listener.setText(StopWatchTimer.formatTime(time, decimals));
    }
    
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
    
    public int getLap() {
        lapTime = time - prevTime;
        prevTime = time;
        return lapTime;
    }
    
    public int getTime() {
        return time;
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
