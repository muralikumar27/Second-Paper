package com.murali.secondpaper.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "work_vaults", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "created_by"})})
public class WorkVault {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    private User createdBy;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "workVault", cascade = CascadeType.ALL)
    private Set<TeamVault> teamVaults;
}
