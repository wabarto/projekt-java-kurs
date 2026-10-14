public class AppConfigEager {

    // tworzone przy INICJALIZACJI KLASY - to robi jvm raz
    private static final AppConfigEager INSTANCE = new AppConfigEager();

    private String url = "jdbc:postgresql://localhost:5432/kurs";
    private final int maxConnection = 10;

    // prywatny konstruktor - nikt z zewnatrz nie zrobi new AppConfigEager()
    private AppConfigEager() {
        System.out.println("eager");
    }

    public static AppConfigEager getInstance() {
        return INSTANCE;
    }

}
