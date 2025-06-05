package clashclass.battle.timer;

public class TimerGameImpl implements Timer {
    private long startTime;
    private long endTime;

    @Override
    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void stop() {
        this.endTime = System.currentTimeMillis() - startTime;
    }

    @Override
    public void onFinished() {

    }

    @Override
    public long getElapsedTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}
