package com.murali.secondpaper.model;

import com.murali.secondpaper.enums.VaultType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamVaultDto {
    @NotNull
    private String name;
    @NotNull
    private UUID workVaultId;
    @NotNull
    private VaultType vaultType;
}
