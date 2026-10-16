public record OrderCancelled(String orderId, String reason) implements OrderEvent{
    @Override
    public String type() {
        return "ORDER_CANCELLED";
    }
}
