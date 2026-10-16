public record OrderPlaced(String orderId, String customerId, double total) implements OrderEvent {
    @Override
    public String type() {
        return "ORDER_PLACED";
    }
}
