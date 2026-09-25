import java.util.List;
import java.util.Timer;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class ThreadsDemo {

    // 3
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
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

//        Thread monitorThread = new Thread(() -> {
//            while (true) {
//                System.out.println("monitoring...");
//                try {
//                    Thread.sleep(300);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//        monitorThread.setDaemon(true);
//        monitorThread.start();


        LongAdder adder = new LongAdder();
        adder.increment();
        adder.add(5);
        System.out.println(adder.sum());


        Account accountA = new Account("A", 1000);
        Account accountB = new Account("B", 1000);

//        new Thread(() -> transferAccount(accountA, accountB, 100), "przelew1").start();
//        new Thread(() -> transferAccount(accountB, accountA, 200), "przelew2").start();

        new Thread(() -> {
            try {
                transferWithRetry(accountA, accountB, 100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "przelew1").start();
        new Thread(() -> {
            try {
                transferWithRetry(accountB, accountA, 200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "przelew2").start();


        ExecutorService constant = Executors.newFixedThreadPool(4);
        ExecutorService cached = Executors.newCachedThreadPool();
        ExecutorService single = Executors.newSingleThreadExecutor();
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);


        for (int i =0; i < 10; i++) {
            int number = i;
            constant.submit(() -> {
                System.out.printf("task number %s, thread %s%n", number, Thread.currentThread().getName());
            });
        }

        Future<Integer> result = constant.submit(() -> {
            Thread.sleep(500);
            return 50;
        });

        System.out.println("do something");
        System.out.println(result.isDone());

        Integer value = result.get(2, TimeUnit.SECONDS);

        System.out.println(value);






        List<Callable<String>> tasks = List.of(
                () -> "result A",
                () -> "result B",
                () -> "result C"
        );

        List<Future<String>> results = constant.invokeAll(tasks);
        for (Future<String> f : results) {
            System.out.println(f.get());
        }


        String fastest = constant.invokeAny(tasks);
        System.out.println(fastest);

        constant.shutdown();




        try {
            if (!constant.awaitTermination(10, TimeUnit.SECONDS)) {
                constant.shutdownNow();
            }
        } catch (InterruptedException interruptedException) {
            constant.shutdownNow();
            Thread.currentThread().interrupt();
        }


        scheduled.schedule(() -> System.out.println("reminder"), 2, TimeUnit.SECONDS);

        ScheduledFuture<?> heartbeat = scheduled.scheduleAtFixedRate(
                () -> System.out.println("heartbeat"),
                0, 1, TimeUnit.SECONDS
                );


        scheduled.scheduleWithFixedDelay(
                () -> System.out.println("reading something"),
                0, 1, TimeUnit.SECONDS
        );


        heartbeat.cancel(false);
        scheduled.shutdown();


        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "test";
                });

        System.out.println("do something");
        System.out.println(task3.join());

        CompletableFuture<Void> notification = CompletableFuture.runAsync(() -> System.out.println("notification"));


        ExecutorService pool = Executors.newFixedThreadPool(20);

        CompletableFuture<String> request = CompletableFuture.supplyAsync(() -> "get something from api", pool);

        CompletableFuture<Integer> length = CompletableFuture
                .supplyAsync(() -> "name")
                .thenApply(String::trim)
                .thenApply(String::toUpperCase)
                .thenApply(String::length);

        System.out.println(length.join());

        CompletableFuture<CompletableFuture<String>> wrong = findId("123").thenApply(id -> fetchData(id));

        CompletableFuture<String> good = findId("123").thenCompose(id -> fetchData(id));



        CompletableFuture<String> user = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "anna";
        });
        CompletableFuture<String> orders = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "orders";
        });
        CompletableFuture<String> weather = CompletableFuture.supplyAsync(() -> "18c");


        String profiles = user
                .thenCombine(orders, (u, z) -> u + "-" + z)
                .thenCombine(weather, (uz, p) -> uz + ", " + p)
                .join();

        System.out.println(profiles);


        List<String> logins = List.of("anna", "bartek", "piotrek");

        List<CompletableFuture<String>> tasks2 = logins.stream()
                .map(login -> CompletableFuture.supplyAsync(() -> "profiles " + login))
                .toList();



        CompletableFuture.allOf(tasks2.toArray(new CompletableFuture[0])).join();

        List<String> profiles2 = tasks2.stream()
                .map(CompletableFuture::join)
                .toList();

        System.out.println(profiles2);



        CompletableFuture.supplyAsync(() -> "done")
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> "zapis do bazy")
                .thenRun(() -> System.out.println("done"));


        CompletableFuture<Object> fastest1 = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> "1"),
                CompletableFuture.supplyAsync(() -> "2"),
                CompletableFuture.supplyAsync(() -> "3")
        );

        System.out.println(fastest1.join());


        String result3 = CompletableFuture.supplyAsync(() -> {
            if (Math.random() > 0.5) {
                throw new RuntimeException("123");
            }
            return "dane";

        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return "dane z cache";
        }).join();

        System.out.println(result3);


       String handled = CompletableFuture.supplyAsync(() -> "doSomething")
                .handle((data, ex) -> {
                    if (ex != null) {
                        System.out.println(ex.getMessage());
                        return "value";
                    }

                    return data;
                }).join();

        System.out.println(handled);

        CompletableFuture.supplyAsync(() -> "doSomething")
                .whenComplete((data, ex) -> {
                    if (ex != null) {
                        System.out.println(ex.getMessage());
                    }

                    System.out.println("ready" + data);
                }).join();


    }

    static CompletableFuture<Long> findId (String login) {
        return CompletableFuture.supplyAsync(() -> 42L);
    }

    static CompletableFuture<String> fetchData (Long id) {
        return CompletableFuture.supplyAsync(() -> "dane" + id);
    }


//    static void transferAccount(Account from, Account to, double amount) {
//        synchronized (from) {
//            System.out.printf("%s: from %s, to %s", Thread.currentThread().getName(), from.getId(), to.getId());
//
//            synchronized (to) {
//                from.withdraw(amount);
//                to.deposit(amount);
//                System.out.printf("%s: transferred from %s, to %s", Thread.currentThread().getName(), from.getId(), to.getId());
//            }
//        }
//    }

    static void transferAccount(Account from, Account to, double amount) {
        Account first = from.getId().compareTo(to.getId()) < 0 ? from : to;
        Account second = (first == from) ? to : from;
        synchronized (first) {
            System.out.printf("%s: from %s, to %s", Thread.currentThread().getName(), from.getId(), to.getId());

            synchronized (second) {
                from.withdraw(amount);
                to.deposit(amount);
                System.out.printf("%s: transferred from %s, to %s", Thread.currentThread().getName(), from.getId(), to.getId());
            }
        }
    }

    static boolean tryTransfer(Account from, Account to, double amount) throws InterruptedException {
        if (!from.getLock().tryLock(200, TimeUnit.MILLISECONDS)) {
            return false;
        }

        try {
            if (!to.getLock().tryLock()) {
                return false;
            }

            try {
                from.withdraw(amount);
                to.deposit(amount);
                System.out.printf("%s: transferred from %s, to %s%n", Thread.currentThread().getName(), from.getId(), to.getId());
                return true;
            } finally {
                to.getLock().unlock();
            }
        } finally {
            from.getLock().unlock();
        }
    }


    static void transferWithRetry(Account from, Account to, double amount) throws InterruptedException {
        int attempt = 0;

        while (!tryTransfer(from, to, amount)) {
            attempt++;
            System.out.printf("attempt: %s %s%n", attempt, Thread.currentThread().getName());
        }
    }


}
