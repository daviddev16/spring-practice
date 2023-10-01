package com.daviddev16.user;

import com.daviddev16.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "users", uniqueConstraints =
        { @UniqueConstraint(name = "users_email_unique", columnNames = {"email"}),
          @UniqueConstraint(name = "users_username_unique", columnNames = {"username"}) }
)
public class User {

    /* DEFINIÇÕES PRINCIPAIS */
    @Id
    @Column(
            name = "user_id",
            updatable = false
    )
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq"
    )
    private Long id;


    @Column(
            nullable = false,
            updatable = false, /* Não poderá ser alterado */
            columnDefinition = "TEXT"
    )
    private String email;


    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;


    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn
            (
                name = "user_id",
                referencedColumnName = "user_id",
                foreignKey = @ForeignKey(name = "user_id_fkey")
            ),
            inverseJoinColumns = @JoinColumn
            (
                name = "role_id",
                referencedColumnName = "role_id",
                foreignKey = @ForeignKey(name = "role_id_fkey")
            )
    )
    private Collection<Role> roles;


    @Column(
            nullable = false,
            name = "first_name",
            columnDefinition = "TEXT"
    )
    private String firstName;


    @Column(
            nullable = false,
            name = "last_name",
            columnDefinition = "TEXT"
    )
    private String lastName;


    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt = LocalDateTime.now();


    private boolean enabled = true;


}
