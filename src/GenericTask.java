import java.util.Optional;

public interface GenericTask extends Runnable {

    long getExecutionTime();
    boolean shouldSchedule();


    Optional<GenericTask> getNextTaskInstance();


}
