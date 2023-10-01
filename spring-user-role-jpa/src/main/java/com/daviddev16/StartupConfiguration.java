package com.daviddev16;

import com.daviddev16.privilege.PrivilegeService;
import com.daviddev16.role.Role;
import com.daviddev16.role.RoleService;
import com.daviddev16.user.User;
import com.daviddev16.user.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartupConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    private RoleService roleService;
    private PrivilegeService privilegeService;
    private UserRepository userRepository;

    @Autowired
    public StartupConfiguration(RoleService roleService,
                                PrivilegeService privilegeService,
                                UserRepository userRepository)
    {
        this.roleService = roleService;
        this.privilegeService = privilegeService;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        privilegeService.initializeDefaultPrivileges();
        roleService.initializeDefaultRoles();

        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {

            User user = new User();

            List<Role> roles = new ArrayList<>();

            roles.add( (faker.number().numberBetween(0,1) == 0) ?
                    roleService.moderator() : roleService.costumer()
            );

            user.setRoles(roles);

            user.setFirstName(faker.name()
                    .firstName());
            user.setLastName(faker.name()
                    .lastName());
            user.setUsername(faker.name()
                    .username());
            user.setEmail(faker.name().firstName()+faker.name()
                    .prefix()+faker.number()
                    .numberBetween(20,30)+"@gmail.com");

            userRepository.save(user);

        }

    }

}
