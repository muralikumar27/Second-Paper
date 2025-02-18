package com.murali.secondpaper.entity;

import com.murali.secondpaper.enums.BlockType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "data_blocks")
public class DataBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //@Column(columnDefinition = "jsonb")
    private String  properties;

    @Enumerated(EnumType.STRING)
    private BlockType type;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate LastModifiedAt;

    @OneToMany(mappedBy = "dataBlock", cascade = CascadeType.ALL)
    private List<FileEntity> files;

    @ManyToOne
    @JoinColumn(name = "last_modified_by", referencedColumnName = "id", nullable = false)
    private User LastModifiedBy;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private DataBlock parent;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    private User user;

    // For now the idea is there is no need for workvault relation
    @ManyToOne
    @JoinColumn(name = "team_vault", referencedColumnName = "id")
    private TeamVault teamVault;

}
