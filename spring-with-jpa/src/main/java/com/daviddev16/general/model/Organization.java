package com.daviddev16.general.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Organization")
@Table(
        name = "organizations",
        uniqueConstraints = {
                @UniqueConstraint(name = "suffix_name_unique", columnNames = {"suffix_name"})
        }
)
public class Organization {

    @Id
    @SequenceGenerator(
            name = "organization_id_seq",
            sequenceName = "organization_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "organization_id_seq"
    )
    @Column(
            name = "organization_id",
            updatable = false
    )
    private Long id;

    /* Sufixo é usado para o login na organização, usando o
       seguinte formato: <user_name>@<suffix_name> */
    @Column(
            name = "suffix_name",
            columnDefinition = "text",
            nullable = false
    )
    private String suffixName;

    @Column(
            name = "display_name",
            columnDefinition = "text",
            nullable = false
    )
    private String displayName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "owner_id",
            foreignKey = @ForeignKey(name = "user_fkey")
    )
    private User owner;

}
