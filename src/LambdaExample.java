public class LambdaExample {
    private String name = "example";

    void run() {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println(LambdaExample.this.name);
            }
        };

        Runnable lambda = () -> System.out.println(this.name);
    }
}
