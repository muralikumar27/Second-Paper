package com.murali.secondpaper.util.helper;

import com.murali.secondpaper.entity.WorkVault;
import com.murali.secondpaper.repository.WorkVaultRepository;
import com.murali.secondpaper.service.WorkVaultService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class VaultHelper {
    private final WorkVaultRepository workVaultRepository;

    public VaultHelper(WorkVaultRepository workVaultRepository) {
        this.workVaultRepository = workVaultRepository;
    }


    public WorkVault getWorkVaultById(UUID id) {
        Optional<WorkVault> optional = workVaultRepository.findById(id);
        if (optional.isEmpty())
            throw new IllegalArgumentException("Invalid work vault ID");
        return optional.get();
    }
}
