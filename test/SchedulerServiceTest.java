import org.junit.Test;

public class SchedulerServiceTest {

    @Test
    public void testSchedulerServiceWithInitialDelayTask() throws InterruptedException {


        CustomExecutor customExecutor = new CustomExecutorImpl(5, 10);
        SchedulerService schedulerService = new SchedulerService(customExecutor);
        System.out.println(" Starting test testSchedulerServiceWithInitialDelayTask");
        schedulerService.scheduleInitialDelayTask(()->System.out.println(" Inital delay task1 "),5000);
        schedulerService.scheduleInitialDelayTask(()->System.out.println(" Inital delay task2 "),300);


        Thread.sleep(10000);
    }

    @Test
    public void testSchedulerServiceWithFixedDelayTask() throws InterruptedException {

        CustomExecutor customExecutor = new CustomExecutorImpl(5, 10);
        SchedulerService schedulerService  = new SchedulerService(customExecutor);
        System.out.println(" Starting test testSchedulerServiceWithFixedDelayTask");
        schedulerService.schduleFixedIntervaltask(()->System.out.println(" fixed delay Task 1"),2000,1000);
        schedulerService.schduleFixedIntervaltask(()->System.out.println(" fixed delay task 2 "), 4000, 1000);

        while(true){

        }
    }
}
