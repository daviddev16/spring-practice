package com.daviddev16.general.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@Entity(name = "Role")
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_id_seq",
            sequenceName = "role_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "role_id_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "role_id",
            updatable = false
    )
    @Getter
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    @Getter
    private String name;

    @ManyToMany(mappedBy = "roles" /* = User.roles */)
    private Collection<User> users;

}
