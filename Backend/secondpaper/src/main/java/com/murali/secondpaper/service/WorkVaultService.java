package com.murali.secondpaper.service;

import com.murali.secondpaper.entity.TeamVault;
import com.murali.secondpaper.entity.User;
import com.murali.secondpaper.entity.WorkVault;
import com.murali.secondpaper.enums.VaultType;
import com.murali.secondpaper.exception.AlreadyExistsException;
import com.murali.secondpaper.repository.WorkVaultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class WorkVaultService {
    private final WorkVaultRepository workVaultRepository;
    private final TeamVaultService teamVaultService;
    private final UserService userService;

    public WorkVaultService(WorkVaultRepository workVaultRepository, TeamVaultService teamVaultService, UserService userService) {
        this.workVaultRepository = workVaultRepository;
        this.teamVaultService = teamVaultService;
        this.userService = userService;
    }

    @Transactional
    public WorkVault createWorkVault(String vaultName, VaultType type, UUID id) {

        User user = userService.getUserById(id);

        if (workVaultRepository.existsByNameAndCreatedBy_id(vaultName, id))
            throw new AlreadyExistsException("Work vault with this name already exists");

        WorkVault workVault = new WorkVault();

        TeamVault teamVault = teamVaultService.createTeamVault(vaultName, type, user);

        teamVault.setWorkVault(workVault);

        Set<TeamVault> set = new HashSet<>();
        set.add(teamVault);

        workVault.setName(vaultName);
        workVault.setCreatedAt(LocalDateTime.now());
        workVault.setCreatedBy(user);
        workVault.setTeamVaults(set);

        workVaultRepository.save(workVault);
        return workVault;
    }
    public void saveWorkVault(WorkVault workVault) {
        workVaultRepository.save(workVault);
    }

    public WorkVault getWorkVaultById(UUID id) {

        Optional<WorkVault> optional = workVaultRepository.findById(id);
        if (optional.isEmpty())
            throw new IllegalArgumentException("No work vault for this ID");
        return optional.get();
    }
}
