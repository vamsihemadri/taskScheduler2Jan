public class SchedulerService {

    Scheduler scheduler;

    public SchedulerService(CustomExecutor executor) {
        this.scheduler = new Scheduler(executor);

        new Thread(scheduler).start();
        //executor.execute(scheduler);
    }


    void schduleFixedIntervaltask(Runnable runnable, long interval, long initialDelay){

        scheduler.add(new FixedIntervalTask(runnable,interval,System.currentTimeMillis()+initialDelay));
    }

    void scheduleInitialDelayTask(Runnable runnable, long initalDelay){
        scheduler.add(new IntialDelayTask(runnable,0,System.currentTimeMillis()+ initalDelay));
    }
}
