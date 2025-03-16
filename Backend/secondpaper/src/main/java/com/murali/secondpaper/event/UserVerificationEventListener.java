package com.murali.secondpaper.event;

import com.murali.secondpaper.entity.User;
import com.murali.secondpaper.entity.WorkVault;
import com.murali.secondpaper.service.WorkVaultService;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserVerificationEventListener implements ApplicationListener<UserVerificationEvent> {

    private final WorkVaultService workVaultService;

    public UserVerificationEventListener(WorkVaultService workVaultService) {
        this.workVaultService = workVaultService;
    }
    @Override
    public void onApplicationEvent(UserVerificationEvent event) {
        User user = (User) event.getSource();
        workVaultService.createWorkVault(event.getWorkVaultName(), event.getVaultType(), user.getId());
    }
}
