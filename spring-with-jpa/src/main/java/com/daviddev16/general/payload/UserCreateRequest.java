package com.daviddev16.general.payload;

import com.daviddev16.general.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateRequest {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<Role> roles;

}
