import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Scheduler implements Runnable {


    Queue<GenericTask> queue;
    ReentrantLock lock;
    Condition isNewTaskAvailable;

    CustomExecutor executor;
    //ExecutorService executorService;

    public Scheduler(CustomExecutor executor) {
        lock = new ReentrantLock();
        isNewTaskAvailable = lock.newCondition();
        queue = new PriorityQueue<>(Comparator.comparingLong(GenericTask::getExecutionTime));

        this.executor = executor;
        //executorService = Executors.newFixedThreadPool(5);

    }


    void add(GenericTask task) {
        lock.lock();
        try {
            queue.offer(task);
            isNewTaskAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        System.out.println("Scheduler started!! ");
        while (true) {
            lock.lock();
            try {
                long sleep = getSleepTimeUntilNextTask();
                while (sleep > 0) {

                    isNewTaskAvailable.await(sleep, TimeUnit.MILLISECONDS);
                    sleep = getSleepTimeUntilNextTask();
                }
                GenericTask nextTaskToExecute = queue.remove();
                this.executeTask(nextTaskToExecute);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }
    }

    private void executeTask(GenericTask task) {
        executor.execute(task);
        //executorService.execute(task);
        Optional<GenericTask> nextTaskOptional = task.getNextTaskInstance();
        if (nextTaskOptional.isPresent()) {
            queue.offer(nextTaskOptional.get());
            isNewTaskAvailable.signalAll();
        }
    }

    private long getSleepTimeUntilNextTask() {
        Long returnTime = Long.MAX_VALUE;
        if(!queue.isEmpty()){
            returnTime = queue.peek().getExecutionTime()- System.currentTimeMillis();;
        }
        return returnTime;
    }

}
