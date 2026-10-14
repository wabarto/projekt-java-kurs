public enum AppConfigEnum {
    INSTANCE;

    private String url = "jdbc:postgresql://localhost:5432/kurs";
    private final int maxConnection = 10;

    AppConfigEnum() {
        System.out.println("enum");
    }
}
