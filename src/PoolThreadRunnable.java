import java.util.concurrent.ArrayBlockingQueue;

public class PoolThreadRunnable implements Runnable{

    boolean isStopped;
    Thread thread;
    ArrayBlockingQueue taskQueue;

    public PoolThreadRunnable(ArrayBlockingQueue taskQueue) {
        isStopped = false;
        this.taskQueue = taskQueue;

    }

    @Override
    public void run() {
        while(!isStopped){

            try {
                Runnable comand = (Runnable) taskQueue.take();
                comand.run();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public  void forceStop(){
        this.isStopped = false;
        this.thread.interrupt();
    }
}
