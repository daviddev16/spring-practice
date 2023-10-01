package com.daviddev16.role;

import com.daviddev16.privilege.Privilege;
import com.daviddev16.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Role")
@Table(name = "roles", uniqueConstraints =
        { @UniqueConstraint(name = "role_name_unique", columnNames = {"name"}) }
)
public class Role {

    @Id
    @Column(
            name = "role_id",
            updatable = false
    )
    @SequenceGenerator(
            name = "role_id_seq",
            sequenceName = "role_id_seq"
    )
    @GeneratedValue(generator = "role_id_seq")
    private Long id;

    @Column(
            updatable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;


    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn
            (
                name = "role_id",
                referencedColumnName = "role_id",
                foreignKey = @ForeignKey(name = "role_id_fkey")
            ),
            inverseJoinColumns = @JoinColumn
            (
                name = "privilege_id",
                referencedColumnName = "privilege_id",
                foreignKey = @ForeignKey(name = "privilege_id_fkey")
            )
    )
    private Collection<Privilege> privileges;

    public Role(String name) {
        this.name = name;
    }

}
