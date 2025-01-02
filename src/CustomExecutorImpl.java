import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class CustomExecutorImpl implements CustomExecutor {


    ArrayBlockingQueue taskQueue;
    boolean isStopped;
    int numThreads;
    int maxQueueSize;
    List<PoolThreadRunnable> runnableList;


    public CustomExecutorImpl(int numThreads, int maxQueueSize){
        this.numThreads  = numThreads;
        this.maxQueueSize = maxQueueSize;
        isStopped = false;
        taskQueue = new ArrayBlockingQueue(maxQueueSize);
        runnableList  = new ArrayList<>();
        for(int i = 0;i<numThreads;i++){
            runnableList.add(new PoolThreadRunnable(taskQueue));
        }

        for(PoolThreadRunnable runnable: runnableList){
            new Thread(runnable).start();
        }

    }

    @Override
    public synchronized void execute(Runnable runnable) {
        if(isStopped){
            throw new IllegalStateException(" Thread Pool is stopped stage, cant execute ");
        }
        this.taskQueue.add(runnable);
    }

    @Override
    public synchronized void shutDownV0() {
        isStopped  = true;
        for(PoolThreadRunnable runnable: runnableList){
            runnable.forceStop();
        }
    }
}
