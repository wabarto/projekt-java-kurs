public class ExampleTask implements Runnable {
    @Override
    public void run() {
        System.out.println("run: " + Thread.currentThread().getName());
    }
}
