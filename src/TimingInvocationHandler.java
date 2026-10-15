import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimingInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();

        Object result = method.invoke(target, args);
        System.out.println("[CZAS] " + (System.nanoTime() - start) / 1_000_000.0 + " " + target.getClass().getSimpleName() + " " + method.getName());
        return result;
    }
}
