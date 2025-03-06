package com.murali.secondpaper.factory;

import com.murali.secondpaper.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

//All service can be moved here
@Component
public class ServiceBeanProvider {
    private final ApplicationContext applicationContext;

    public ServiceBeanProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public UserService getUserService() {
        return (UserService) applicationContext.getBean("userService");
    }
}
