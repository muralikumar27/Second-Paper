package com.murali.secondpaper.event;

import com.murali.secondpaper.entity.User;
import com.murali.secondpaper.enums.VaultType;
import com.murali.secondpaper.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
@Setter
public class UserVerificationEvent extends ApplicationEvent {

    private String workVaultName;
    private VaultType vaultType;
    public UserVerificationEvent(User user, String workVaultName, VaultType vaultType) {
        super(user);
        this.workVaultName = workVaultName;
        this.vaultType = vaultType;
    }
}
