package com.daviddev16.general.service;

import com.daviddev16.general.model.Role;

import java.util.Optional;

public interface RoleService {

    Role createRole( Role role );

    Optional<Role> findRoleById( Long id );

}
