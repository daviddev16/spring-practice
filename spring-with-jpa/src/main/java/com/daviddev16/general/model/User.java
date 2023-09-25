package com.daviddev16.general.model;

import com.daviddev16.general.payload.UserCreateRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_unique", columnNames = {"email"})
        }
)
@Entity(name = "User")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_id_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "user_id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "email",
            columnDefinition = "text",
            nullable = false
    )
    private String email;

    @Column(
            name = "first_name",
            columnDefinition = "text",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            columnDefinition = "text",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "password",
            columnDefinition = "text",
            nullable = false
    )
    private String password;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(
            name = "enabled",
            nullable = false
    )
    private boolean enabled = true;

    /* Organization.owner */
    @OneToMany(mappedBy = "owner")
    private Set<Organization> organizations = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn
            (
                name = "user_id",
                referencedColumnName = "user_id",
                foreignKey = @ForeignKey(name = "user_id_fk")
            ),
            inverseJoinColumns = @JoinColumn
            (
                name = "role_id",
                referencedColumnName = "role_id",
                foreignKey = @ForeignKey(name = "role_id_fk")
            )
    )
    /* users <--> users_roles <--> roles */
    private Set<Role> roles = new HashSet<>();

    public static User of(UserCreateRequest createRequest) {
        User userFromRequest = new User();
        BeanUtils.copyProperties(createRequest, userFromRequest);
        return userFromRequest;
    }

}
