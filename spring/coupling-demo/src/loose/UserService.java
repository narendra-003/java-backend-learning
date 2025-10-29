package loose;

public class UserService {

    public NotificationService notificationService;

    public UserService() {
    }

    // Constructor Injection
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyUser(String message) {
        notificationService.send("Notification hello");
    }

    // Setter Injection
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
