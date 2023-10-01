package com.daviddev16.role;

import com.daviddev16.privilege.Privilege;
import com.daviddev16.privilege.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.List.*;

@Service
public class RoleService {

    private RoleRepository roleRepository;
    private PrivilegeService privilegeService;

    private Role admininstatorRole;
    private Role moderatorRole;
    private Role customerRole;

    @Autowired
    public RoleService(RoleRepository roleRepository,
                       PrivilegeService privilegeService)
    {
        this.roleRepository = roleRepository;
        this.privilegeService = privilegeService;
    }

    /* criando cargos padrões */
    public void initializeDefaultRoles() {
        admininstatorRole = createRoleIfNotFound("ROLE_ADMINISTRATOR", of(
                privilegeService.admin())
        );
        moderatorRole = createRoleIfNotFound("ROLE_MODERATOR", of(
                privilegeService.read(),
                privilegeService.write())
        );
        customerRole = createRoleIfNotFound("ROLE_CUSTOMER", of(
                privilegeService.read()
        ));
    }

    public Role createRoleIfNotFound(String name, List<Privilege> privileges) {
        Role role = roleRepository.findRoleByName(name);
        if (role == null) {
            Role newRole = new Role(name);
            newRole.setPrivileges(privileges);
            return roleRepository.save(newRole);
        }
        return role;
    }

    public Role administrator() {
        return admininstatorRole;
    }

    public Role moderator() {
        return moderatorRole;
    }

    public Role costumer() {
        return customerRole;
    }
}
