import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Proxy;
import java.util.*;

public class DemoPatterns {
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


    }
}
