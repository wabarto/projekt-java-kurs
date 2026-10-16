public class EmailNotificationListener implements OrderEventListener {


    @Override
    public void onEvent(OrderEvent event) {
        if (event instanceof OrderPlaced) {
            System.out.println("Email confirmation sent");
        }

        if (event instanceof OrderCancelled) {
            System.out.println("Email cancellation sent");
        }
    }
}
