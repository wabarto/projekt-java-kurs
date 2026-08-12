public class SmsNotifier implements Notifier {

    private final String phoneNumber;

    public SmsNotifier(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        String sms = message.length() > MAX_MESSAGE_LENGTH
                ? message.substring(0, MAX_MESSAGE_LENGTH)
                : message;

        System.out.println("SMS -> " + phoneNumber + ": " + sms);
    }

    @Override
    public String getChannel() {
        return "SMS";
    }
}
