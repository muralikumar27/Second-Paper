package com.murali.secondpaper.service;

import com.murali.secondpaper.entity.TeamVault;
import com.murali.secondpaper.entity.User;
import com.murali.secondpaper.enums.VaultType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TeamVaultService {
    public TeamVault createTeamVault(String name, VaultType type, User user) {
        TeamVault teamVault = new TeamVault();
        teamVault.setName(name);
        teamVault.setCreatedBy(user);
        teamVault.setCreatedAt(LocalDate.now());
        teamVault.setType(type);

        return teamVault;
    }
}
