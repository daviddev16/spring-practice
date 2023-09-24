package com.daviddev16.general.view;

import com.daviddev16.general.model.Organization;
import com.daviddev16.general.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserGenericResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime createdAt;
    private boolean enabled = true;
    private Set<Organization> organizations;
    private Set<Role> roles;

}
