package com.daviddev16.general;

import com.daviddev16.general.model.Role;
import com.daviddev16.general.service.role.RoleService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultRoles implements CommandLineRunner {

    private final RoleService roleService;

    @Autowired
    public DefaultRoles(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception
    {
        /* usuário administrador */
        getRoleService().createRole(
                Role.builder()
                        .name("Administrador")
                        .build());
        /* usuário comum */
        getRoleService().createRole(
                Role.builder()
                        .name("Customer")
                        .build());
    }

    private RoleService getRoleService() {
        return roleService;
    }
}
