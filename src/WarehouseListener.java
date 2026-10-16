public class WarehouseListener implements OrderEventListener{
    @Override
    public void onEvent(OrderEvent event) {
        System.out.println("Reservation");
    }
}
