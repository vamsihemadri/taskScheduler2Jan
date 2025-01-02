import java.util.Optional;

public class IntialDelayTask implements GenericTask {


    Runnable runnable;
    long interval;
    long executionTime;

    public IntialDelayTask(Runnable runnable, long interval, long executionTime) {
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
        return false;
    }

    @Override
    public Optional<GenericTask> getNextTaskInstance() {
        return Optional.empty();
    }

    @Override
    public void run() {
        runnable.run();
    }
}
