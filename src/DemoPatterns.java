import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Proxy;
import java.util.*;

public class DemoPatterns {

    private static final Map<String, String> JSON = Map.of("Content-Type", "application/json");

    private static final String TOKEN = "Bearer valid-token-123";

    static RequestHandler buildChain () {
        RequestHandler auth = new AuthHandler();
        auth.setNext(new ContentTypeHandler())
                .setNext(new BusinessHandler());
        return auth;
    }

    public static void main(String[] args) throws IOException {
        // builder

        // metoda wytworcza
        List<String> names = List.of("x", "y");


        // dekorator
        BufferedReader reader = new BufferedReader(new StringReader("123"));
        reader.readLine();


        // strategia
        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Comparator.naturalOrder());
        sorted.sort(Comparator.comparingInt(String::length).reversed());


        // command
        Runnable task = () -> System.out.println("Runnable");
        task.run();


        // adapter
        String[] table = { "x", "y", "z"};
        List<String> list = Arrays.asList(table); // asList - adapter


        AppConfigEager eaager1 = AppConfigEager.getInstance();
        AppConfigEager eaager2 = AppConfigEager.getInstance();
        System.out.println(eaager1 == eaager2);

        AppConfigLazy appConfigLazy1 = AppConfigLazy.getInstance();
        AppConfigLazy appConfigLazy2 = AppConfigLazy.getInstance();
        System.out.println(appConfigLazy1 == appConfigLazy2);

        AppConfigEnum appConfigEnum1 = AppConfigEnum.INSTANCE;
        AppConfigEnum appConfigEnum2 = AppConfigEnum.INSTANCE;

        System.out.println(appConfigEnum1 == appConfigEnum2);


        Email email = new Email.Builder()
                .from("from")
                .to("to")
                .subject("subject")
                .build();


        System.out.println(email);

        UserRepository real = new DatabaseUserRepository();
        UserRepository cached = new CachingUserRepositoryProxy(real);
        UserRepository logged = new LoggingUserRepositoryProxy(cached);

        Optional<User> user = logged.findById(5L);

        UserRepository dynamic = (UserRepository) Proxy.newProxyInstance(
                UserRepository.class.getClassLoader(),
                new Class<?>[] { UserRepository.class },
                new TimingInvocationHandler(new DatabaseUserRepository())
        );

        dynamic.findById(5L);
        dynamic.findAll();


        DiscountStrategy noDiscount = (price, quantity) -> price * quantity;
        DiscountStrategy summerSale = (price, quantity) -> Math.max(price * quantity - 50, 0);
        DiscountStrategy loyalty = (price, quantity) -> price * quantity * 0.9;


        Map<String, DiscountStrategy> strategies = new LinkedHashMap<>();
        strategies.put("STANDARD", noDiscount);
        strategies.put("SALE", summerSale);
        strategies.put("LOYALTY", loyalty);


        double price = 49.99;
        int quantity = 12;

        for (Map.Entry<String, DiscountStrategy> entry : strategies.entrySet()) {
            PriceCalculator calculator = new PriceCalculator(entry.getValue());
            System.out.println(calculator.calculate(price, quantity));
        }



        // placeOrder: zapisywac zamowienie, wysylac potwierdzenie zamowienia, zarezerwowac towar na magazynie, trackowac



        OrderEventBus orderEventBus = new OrderEventBus();
        WarehouseListener warehouseListener = new WarehouseListener();

        orderEventBus.subscribe("ORDER_PLACED", warehouseListener);
        orderEventBus.subscribe("ORDER_CANCELLED", new EmailNotificationListener());
        orderEventBus.subscribe("ORDER_PLACED", new EmailNotificationListener());


        OrderService orderService = new OrderService(orderEventBus);

        orderService.placeOrder("001", "32", 200);

        orderService.cancelOrder("001", "123");


        buildChain().handle(new ApiRequest("/api/todos", TOKEN, "{\"title\":\"test\"}", JSON));
        buildChain().handle(new ApiRequest("/api/todos", null, "{\"title\":\"test\"}", JSON));


    }
}
