public interface CustomExecutor {

    void execute(Runnable runnable);

    void shutDownV1();
}
