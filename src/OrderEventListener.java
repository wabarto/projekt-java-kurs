@FunctionalInterface
public interface OrderEventListener {
    void onEvent(OrderEvent event);
}
