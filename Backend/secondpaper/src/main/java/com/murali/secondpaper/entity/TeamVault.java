package com.murali.secondpaper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.murali.secondpaper.enums.VaultType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "team_vaults", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "work_vault_id"})})
public class TeamVault {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private VaultType type;

    @Column(nullable = false)
    private LocalDate createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    private User createdBy;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "work_vault_id", referencedColumnName = "id", nullable = false)
    private WorkVault workVault;

}
