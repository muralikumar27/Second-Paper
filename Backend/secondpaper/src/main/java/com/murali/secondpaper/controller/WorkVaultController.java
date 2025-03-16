package com.murali.secondpaper.controller;

import com.murali.secondpaper.entity.WorkVault;
import com.murali.secondpaper.enums.VaultType;
import com.murali.secondpaper.service.WorkVaultService;
import com.murali.secondpaper.util.TestUserProvider;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v0/work-vault")
public class WorkVaultController {

    private final WorkVaultService workVaultService;

    public WorkVaultController(WorkVaultService workVaultService) {
        this.workVaultService = workVaultService;
    }

    @PostMapping("/{vaultName}")
    public WorkVault createWorkVault(@PathVariable String vaultName) {
        return workVaultService.createWorkVault(vaultName, VaultType.DEFAULT, TestUserProvider.userId);
    }
}