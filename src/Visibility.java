public class Visibility {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("cos tam");
        }
    }

    public void stop() {
        running = false;
    }
}
