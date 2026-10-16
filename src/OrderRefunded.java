public record OrderRefunded(String orderId, double amount) implements OrderEvent {
    @Override
    public String type() {
        return "ORDER_REFUNDED";
    }
}
