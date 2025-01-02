import java.util.Optional;

public class FixedIntervalTask implements GenericTask {


    Runnable runnable;
    long interval;
    long executionTime;

    public FixedIntervalTask(Runnable runnable, long interval, long executionTime) {
        this.runnable = runnable;
        this.interval = interval;
        this.executionTime = executionTime;
    }

    @Override
    public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public boolean shouldSchedule() {
        return true;
    }

    @Override
    public Optional<GenericTask> getNextTaskInstance() {
        return Optional.of(new FixedIntervalTask(runnable,interval,executionTime + interval));

    }

    @Override
    public void run() {
        runnable.run();
    }
}
