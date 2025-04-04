package clashclass.battle.timer;

import java.util.Optional;

/**
 * Represent the Timer Implementation
 */
public class TimerImpl implements Timer {

    private static final long TIME_UNIT = 1000;  //the time unit is 1 second
    private static final int TIME_LIMIT = 180;//time limit in seconds (3 minutes)
    private static final int RESET=0;
    private long seconds;
    private boolean isRunning;
    private Thread timerThread;


    public TimerImpl() {
        this.isRunning = false;
        this.seconds = RESET;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        if (isRunning) {
            return;
        }
        switchIsRunning();
        seconds = RESET;
        timerThread = new Thread(this::runTimer);
        timerThread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        if (!isRunning) {
            return;
        }
        switchIsRunning();
        if (Optional.ofNullable(timerThread).isPresent() && timerThread.isAlive()) {
            timerThread.interrupt();
            try {
                timerThread.join();     // Wait for the timer thread to finish execution
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Propagate the interrupt status
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFinished() {
        if (isRunning) {
            stop();
        }
    }

    //The method that runs the timer in a separate thread.
    //The timer increments the seconds every second until the time limit is reached.
    private void runTimer() {
        try {
            while (isRunning && seconds < TIME_LIMIT) {
                Thread.sleep(TIME_UNIT);
                seconds++;
                if (seconds >= TIME_LIMIT) {
                    onFinished();
                    break;
                }
            }
        } catch (InterruptedException e) {
            if (isRunning) {
                Thread.currentThread().interrupt();  // Re-interrupt the thread if it's interrupted
            }
        }
    }


    private void switchIsRunning() {
        isRunning = !isRunning;
    }
}
