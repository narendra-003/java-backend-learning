package com.example.loose;

public class UserService {

    NotificationService notificationService;

    public UserService() {
    }

    // Constructor Injection
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyUser(String message) {
        notificationService.send(message);
    }

    // Setter Injection
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
