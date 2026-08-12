public class NoOpNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("TEST: " + message);
    }

    @Override
    public String getChannel() {
        return "test";
    }

    @Override
    public String describe() {
        return "test";
    }
}
