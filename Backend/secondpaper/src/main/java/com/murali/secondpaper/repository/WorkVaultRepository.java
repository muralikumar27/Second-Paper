package com.murali.secondpaper.repository;

import com.murali.secondpaper.entity.WorkVault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkVaultRepository extends JpaRepository<WorkVault, UUID> {

    boolean existsByNameAndCreatedBy_id(String vaultName, UUID id);
}
