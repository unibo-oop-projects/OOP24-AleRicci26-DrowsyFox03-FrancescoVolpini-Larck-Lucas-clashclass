package clashclass.battle.timer;

public class TimerImpl implements Timer {

    private static final long TIME_UNIT = 1000;  //the time unit is 1 second
    private static final int TIME_LIMIT = 180;   //time limit in seconds (3 minutes)
    private long seconds;
    private boolean isRunning;
    private Thread timerThread;


    public TimerImpl() {
        this.isRunning = false;
        this.seconds = 0;
    }


    @Override
    public void start() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        seconds = 0;
        timerThread = new Thread(this::runTimer);
        timerThread.start();
    }


    @Override
    public void stop() {
        if (!isRunning) {
            return;
        }
        isRunning = false;
        if (timerThread != null && timerThread.isAlive()) {
            timerThread.interrupt();
            try {
                timerThread.join();     // Wait for the timer thread to finish execution
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Propagate the interrupt status
            }
        }
    }


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

}
