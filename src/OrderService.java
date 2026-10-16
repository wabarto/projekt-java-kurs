public class OrderService {

    private final OrderEventBus bus;

    // EmailNotificationService
    // WarehouseService
    // AnalyticsService

    public OrderService(OrderEventBus bus) {
        this.bus = bus;
    }

    public void placeOrder(String orderId, String customerId, double total) {
        // notificationService.send()
        //
        bus.publish(new OrderPlaced(orderId, customerId, total));
    }

    public void cancelOrder(String orderId, String reason) {
        bus.publish(new OrderCancelled(orderId, reason));
    }

    public void refundOrder(String orderId, double amount) {
        bus.publish(new OrderRefunded(orderId, amount));
    }

}
