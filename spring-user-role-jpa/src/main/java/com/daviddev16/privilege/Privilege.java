package com.daviddev16.privilege;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Privilege")
@Table(name = "privileges")
public class Privilege {

    @Id
    @Column(
            name = "privilege_id",
            updatable = false
    )
    @SequenceGenerator(
            name = "privilege_id_seq",
            sequenceName = "privilege_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "privilege_id_seq"
    )
    private Long id;

    private String name;

    public Privilege(String name) {
        this.name = name;
    }

}
