import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    private volatile int count = 0;
    private final Object lock = new Object();

    void increment() {
        atomicInteger.incrementAndGet(); // zwraca wartość po zmianie
        atomicInteger.getAndIncrement(); // zwraca PRZED zmianą
        atomicInteger.addAndGet(5);
        atomicInteger.compareAndSet(17, 50);
    }

//    synchronized void increment() {
//        count++;
//    }

//    void increment() {
//        // prepareSomething();
//        synchronized(lock) {
//            count++;
//        }
//        // prepareSomething();
//    }

    int get() {
        return atomicInteger.get();
    }
}
