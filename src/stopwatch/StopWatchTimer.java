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
    
    public StopWatchTimer(Label timerLabel, int decimalPlaces) {
        listener = timerLabel;
        decimals = decimalPlaces;
        
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Platform.runLater(new Runnable(){ 
                    @Override
                    public void run() {
                    listener.setText(StopWatchTimer.formatTime(time++, 
                            decimals));
                    }
                });
            }
        };
        
        timer =  new Timer(1, taskPerformer);
    }
    
    
    /**
     * Start the timer
     */
    public void start() {timer.start();}
    
    /**
     *  Stop the timer
     */
    public void stop() {timer.stop();}
    
    /**
     * Reset the timer to 0
     */
    public void reset() {
        
    }
    
    /**
     * @param time - the current time in milliseconds
     * @return time in hh:mm:ss format
     */
    public static String formatTime(int time, int decimals) {
        return Integer.toString(time);
    }
    
}
