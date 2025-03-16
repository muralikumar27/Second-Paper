package com.murali.secondpaper.repository;

import com.murali.secondpaper.entity.TeamVault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamVaultRepository extends JpaRepository<TeamVault, UUID> {
    boolean existsByNameAndWorkVault_id(String name, UUID workVaultId);
}
