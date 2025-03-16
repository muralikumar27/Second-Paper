package com.murali.secondpaper.service;

import com.murali.secondpaper.entity.TeamVault;
import com.murali.secondpaper.entity.User;
import com.murali.secondpaper.enums.VaultType;
import com.murali.secondpaper.exception.AlreadyExistsException;
import com.murali.secondpaper.model.TeamVaultDto;
import com.murali.secondpaper.repository.TeamVaultRepository;
import com.murali.secondpaper.util.helper.VaultHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TeamVaultService {
    private final UserService userService;
    private final TeamVaultRepository teamVaultRepository;
    private final VaultHelper vaultHelper;

    public TeamVaultService(UserService userService, TeamVaultRepository teamVaultRepository, VaultHelper vaultHelper) {
        this.userService = userService;
        this.teamVaultRepository = teamVaultRepository;
        this.vaultHelper = vaultHelper;
    }

    public TeamVault createTeamVault(String name, VaultType type, User user) {
        TeamVault teamVault = new TeamVault();
        teamVault.setName(name);
        teamVault.setCreatedBy(user);
        teamVault.setCreatedAt(LocalDate.now());
        teamVault.setType(type);

        return teamVault;
    }

    public TeamVault createTeamVault(TeamVaultDto teamVaultDto, UUID userId) {
        if (teamVaultRepository.existsByNameAndWorkVault_id(teamVaultDto.getName(), teamVaultDto.getWorkVaultId()))
            throw new AlreadyExistsException("Team vault with this name already exists");

        TeamVault teamVault = new TeamVault();
        teamVault.setName(teamVaultDto.getName());
        teamVault.setWorkVault(vaultHelper.getWorkVaultById(teamVaultDto.getWorkVaultId()));
        teamVault.setCreatedAt(LocalDate.now());
        teamVault.setCreatedBy(userService.getUserById(userId));
        teamVault.setType(teamVaultDto.getVaultType());

        return teamVaultRepository.save(teamVault);
    }
}
