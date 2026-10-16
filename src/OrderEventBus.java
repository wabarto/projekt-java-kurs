import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrderEventBus {

    private final Map<String, List<OrderEventListener>> listeners = new ConcurrentHashMap<>();

    public void subscribe(String eventType, OrderEventListener listener) {
        listeners.computeIfAbsent(eventType, key -> new CopyOnWriteArrayList<>()).add(listener);
    }

    public void unsubscribe(String eventType, OrderEventListener listener) {
        List<OrderEventListener> forType = listeners.get(eventType);
        if (forType != null) {
            forType.remove(listener);
        }
    }

    public void publish(OrderEvent event) {
        System.out.println("Publish " + event.type() + " " + event.orderId());
        for (OrderEventListener listener : listeners.getOrDefault(event.type(), List.of())) {
            try {
                listener.onEvent(event);
            } catch (RuntimeException ex) {
                System.out.println(ex);
            }
        }
    }
}
