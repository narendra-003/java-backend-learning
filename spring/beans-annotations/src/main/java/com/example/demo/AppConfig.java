package com.example.demo;

import com.example.loose.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {

    // one use case is if you want to run pre & post logic for predefined classes we can do this.
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public LifecycleBean lifecycleBean(NotificationService notificationService) {
        return new LifecycleBean(notificationService);
    }
}
