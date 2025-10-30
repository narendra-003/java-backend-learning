package com.example.loose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("UserServiceSMS")
public class UserService {

//    @Autowired
    NotificationService notificationService;

    public UserService() {
    }

    // Constructor Injection -- can be automatic without autowired if not multiple constructor ambiguity
    @Autowired
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
