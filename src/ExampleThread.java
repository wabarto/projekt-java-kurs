public class ExampleThread extends Thread {

    @Override
    public void run() {
        System.out.println("run " + Thread.currentThread().getName());
    }
}
