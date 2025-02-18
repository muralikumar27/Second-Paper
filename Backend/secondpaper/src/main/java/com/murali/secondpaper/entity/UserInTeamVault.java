package com.murali.secondpaper.entity;

import com.murali.secondpaper.enums.VaultRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "team_vault_user")
public class UserInTeamVault {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_vault_id", referencedColumnName = "id", nullable = false)
    private TeamVault teamVault;

    @Column(nullable = false)
    private VaultRole role;

    @Column(nullable = false)
    private LocalDate joinedOn;
}
