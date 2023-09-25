package com.daviddev16.general.service.impl;

import com.daviddev16.exception.base.GenericAlreadyExistsException;
import com.daviddev16.exception.base.GenericNotFoundException;
import com.daviddev16.general.model.Role;
import com.daviddev16.general.model.User;
import com.daviddev16.general.repository.UserRepository;
import com.daviddev16.general.service.RoleService;
import com.daviddev16.general.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User createUser(User newUser) {

        if (userRepository.findByEmail(newUser.getEmail()).isPresent())
            throw new GenericAlreadyExistsException("Email já cadastrado");

        return userRepository.save(newUser);

    }

    @Override
    public void deleteUser(Long userId) {

        if (!userRepository.existsById(userId))
            throw new GenericNotFoundException("Nenhum usuário com o " +
                    "id " + userId + " foi encontrado.");

        userRepository.deleteById(userId);

    }

    @Override
    public User addRole(Long roleId, Long userId) {

        Optional<Role> optionalRole = roleService.findRoleById(roleId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalRole.isEmpty())
            throw new GenericNotFoundException("Esse cargo não existe, portanto, " +
                    "não pode ser atribuido a um usuário.");

        else if (optionalUser.isEmpty())
            throw new GenericNotFoundException("Nenhum usuário com o " +
                    "id " + userId + " foi encontrado.");

        User user = optionalUser.get();
        user.getRoles().add(optionalRole.get());
        return userRepository.save(user);

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
