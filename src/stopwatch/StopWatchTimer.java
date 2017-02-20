package stopwatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javax.swing.Timer;

/**
 *
 * @author rmaccrimmon
 */
public class StopWatchTimer {
    
    private final Timer timer;
    private final Label listener;
    private final int decimals;
    private int time;
    private Boolean isRunning;
    
    public StopWatchTimer(Label timerLabel, int decimalPlaces) {
        listener = timerLabel;
        decimals = decimalPlaces;
        
        Runnable updateTime = new Runnable(){ 
            @Override
            public void run() {
                listener.setText(StopWatchTimer.formatTime(time++, decimals));
            }
        };
        
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Platform.runLater(updateTime);
            }
        };
        timer =  new Timer(1, taskPerformer);
    }
    
    /**
     * Start the timer
     */
    public void start() {
        timer.start();
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
