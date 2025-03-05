package com.murali.secondpaper.service;

import com.murali.secondpaper.controller.WorkVaultController;
import com.murali.secondpaper.entity.TeamVault;
import com.murali.secondpaper.entity.WorkVault;
import com.murali.secondpaper.enums.VaultType;
import com.murali.secondpaper.repository.WorkVaultRepository;
import com.murali.secondpaper.util.TestUserProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class WorkVaultService {
    private final WorkVaultRepository workVaultRepository;
    private final UserService userService;

    public WorkVaultService(WorkVaultRepository workVaultRepository, UserService userService) {
        this.workVaultRepository = workVaultRepository;
        this.userService = userService;
    }

    //TODO: one user should not have more than one work vault with same name,
    // same for one work vault can't have more than 1 team vault with same name
    @Transactional
    public WorkVault createWorkVault(String vaultName) {

        WorkVault workVault = new WorkVault();

        TeamVault teamVault = new TeamVault();
        teamVault.setType(VaultType.DEFAULT);
        teamVault.setName(vaultName);
        teamVault.setCreatedAt(LocalDate.now());
        teamVault.setCreatedBy(userService.getUserById(TestUserProvider.userId));
        teamVault.setWorkVault(workVault);

        Set<TeamVault> set = new HashSet<>();
        set.add(teamVault);

        workVault.setName(vaultName);
        workVault.setCreatedAt(LocalDateTime.now());
        workVault.setCreatedBy(userService.getUserById(TestUserProvider.userId));
        workVault.setTeamVaults(set);

        workVaultRepository.save(workVault);

        return workVault;
    }
}
