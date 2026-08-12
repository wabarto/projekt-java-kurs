public class EmailNotifier implements Notifier {

    private final String address;

    public EmailNotifier(String address) {
        this.address = address;
    }
    @Override
    public void send(String message) {
        System.out.println("EMAIL -> " + address + ": " + message);
    }

    @Override
    public String getChannel() {
        return "EMAIL";
    }
}
