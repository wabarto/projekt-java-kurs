public class ShopService {
//    private final EmailService emailService = new EmailService();

    private NotificationService notificationService;

    public ShopService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void doSomething() {
        notificationService.send();
    }
}
