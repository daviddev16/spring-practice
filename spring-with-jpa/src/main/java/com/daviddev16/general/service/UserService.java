package com.daviddev16.general.service;

import com.daviddev16.general.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void deleteUser( Long userId );

    User createUser( User user );

    User addRole( Long roleId, Long userId );

    Optional<User> findByEmail( String email );

    Optional<User> findById( Long userId );

    List<User> getAllUsers();

}
