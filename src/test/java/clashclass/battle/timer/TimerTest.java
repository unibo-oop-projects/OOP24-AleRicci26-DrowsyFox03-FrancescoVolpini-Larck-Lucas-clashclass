package clashclass.battle.timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimerTest {

    private TimerImpl timer;

    @BeforeEach
    public void setUp() {
        timer = new TimerImpl();
    }

    @Test
    public void testStartTimer() throws Exception {
        timer.start();
        var isRunningField = TimerImpl.class.getDeclaredField("isRunning");
        isRunningField.setAccessible(true);
        assertTrue((boolean) isRunningField.get(timer), "Timer should be running after start");
        timer.stop();
    }

    @Test
    public void testStopTimer() throws Exception {
        timer.start();
        timer.stop();
        var isRunningField = TimerImpl.class.getDeclaredField("isRunning");
        isRunningField.setAccessible(true);
        assertFalse((boolean) isRunningField.get(timer), "Timer should not be running after stop");
    }

    @Test
    public void testTimerIncrementsSeconds() throws Exception {
        timer.start();
        Thread.sleep(1100);
        var secondsField = TimerImpl.class.getDeclaredField("seconds");
        secondsField.setAccessible(true);
        assertTrue((long) secondsField.get(timer) > 0, "Seconds should increment");
        timer.stop();
    }

    @Test
    public void testOnFinished() throws Exception {
        timer.start();
        timer.onFinished();
        var isRunningField = TimerImpl.class.getDeclaredField("isRunning");
        isRunningField.setAccessible(true);
        assertFalse((boolean) isRunningField.get(timer), "Timer should stop when onFinished is called");
    }

    @Test
    public void testTimeLimit() throws Exception {
        timer.start();
        var secondsField = TimerImpl.class.getDeclaredField("seconds");
        secondsField.setAccessible(true);
        var timeLimitField = TimerImpl.class.getDeclaredField("TIME_LIMIT");
        timeLimitField.setAccessible(true);
        int timeLimit = (int) timeLimitField.get(null);
        secondsField.set(timer, timeLimit - 1);
        Thread.sleep(1200);
        var isRunningField = TimerImpl.class.getDeclaredField("isRunning");
        isRunningField.setAccessible(true);
        assertFalse((boolean) isRunningField.get(timer), "Timer should stop at time limit");
    }
}