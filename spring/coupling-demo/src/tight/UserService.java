package tight;

public class UserService {

    NotificationService notificationService = new NotificationService();

    public void notifyUser(String message) {
        // if we change notification service class then we need to make some changes here also - tight coupling
        notificationService.send("Notification hello");
    }
}
