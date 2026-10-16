public class AnalyticsListener implements OrderEventListener{
    @Override
    public void onEvent(OrderEvent event) {
        System.out.println("Analytics");
    }
}
