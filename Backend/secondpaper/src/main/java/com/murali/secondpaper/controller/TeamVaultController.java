package com.murali.secondpaper.controller;

import com.murali.secondpaper.entity.TeamVault;
import com.murali.secondpaper.model.TeamVaultDto;
import com.murali.secondpaper.service.TeamVaultService;
import com.murali.secondpaper.util.TestUserProvider;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v0/team-vault")
public class TeamVaultController {

    private final TeamVaultService teamVaultService;

    public TeamVaultController(TeamVaultService teamVaultService) {
        this.teamVaultService = teamVaultService;
    }

    @PostMapping()
    public TeamVault createTeamVault(@Valid @RequestBody TeamVaultDto teamVaultDto) {
        return teamVaultService.createTeamVault(teamVaultDto, TestUserProvider.userId);
    }
}
