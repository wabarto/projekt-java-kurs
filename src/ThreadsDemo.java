import java.util.concurrent.atomic.LongAdder;

public class ThreadsDemo {

    // 3
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.get());

        new ExampleThread().start();

        new Thread(new ExampleTask()).start();


        Runnable task1 = () -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + "step " + i);
            }
        };

        System.out.println("---start---");
        Thread a = new Thread(task1, "A");
        Thread b = new Thread(task1, "B");
        a.start();
        b.start();
        a.join();
        b.join();


        System.out.println("---run---");
        new Thread(task1, "C").run();
        new Thread(task1, "D").run();



        // 1

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {

        }


        // 2
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Thread monitorThread = new Thread(() -> {
            while (true) {
                System.out.println("monitoring...");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        monitorThread.setDaemon(true);
        monitorThread.start();


        LongAdder adder = new LongAdder();
        adder.increment();
        adder.add(5);
        System.out.println(adder.sum());





    }
}
