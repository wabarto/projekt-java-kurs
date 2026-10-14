public class AppConfigLazy {

    private static volatile AppConfigLazy instance;
    private String url = "jdbc:postgresql://localhost:5432/kurs";
    private final int maxConnection = 10;

    private AppConfigLazy() {
        System.out.println("lazy");
    }

    public static AppConfigLazy getInstance() {
        if (instance == null) {
            synchronized (AppConfigLazy.class) {
                if (instance == null) {
                    instance = new AppConfigLazy();
                }
            }
        }
        return instance;
    }
 }
