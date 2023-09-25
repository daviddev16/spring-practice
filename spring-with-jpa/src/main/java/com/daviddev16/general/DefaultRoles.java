package com.daviddev16.general;

import com.daviddev16.general.model.Role;
import com.daviddev16.general.service.RoleService;
import com.daviddev16.general.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultRoles implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DefaultRoles(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        createDefaultRoles();
    }

    private void createDefaultRoles() {
        /* usuário Administrator */
        getRoleService().createRole(
                Role.builder()
                        .name("Administrator")
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
