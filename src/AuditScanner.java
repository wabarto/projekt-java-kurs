import java.lang.reflect.Method;

public class AuditScanner {
    public static void scan(Class<?> clazz) {

        // adnotacje na klasie
        if (clazz.isAnnotationPresent(Audited.class)) {
            Audited ann = clazz.getAnnotation((Audited.class));
            System.out.println("Klasa " + clazz.getSimpleName() + " poziom audytu - " + ann.value());
        }

        // adnotacje na metodach

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Audited.class)) {
                Audited ann = method.getAnnotation(Audited.class);
                System.out.println(method.getName() + " poziom " + ann.value() + "loguje kwote " + ann.logAmount());
            }
        }
    }

    public static void main(String[] args) {
        scan(SavingsAccount.class);
    }
}

// @Autowired
