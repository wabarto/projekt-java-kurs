public interface Notifier extends XYZ, XY {


    //stala
    int MAX_MESSAGE_LENGTH = 160;

    // abstrakcyjne
    void send(String message);
    String getChannel();


    // domyslne
    default String describe() {
        return "Powiadomienie kanalem: " + getChannel();
    }


    // narzedziowe
    static Notifier silent() {
        return new NoOpNotifier();
    }
}
