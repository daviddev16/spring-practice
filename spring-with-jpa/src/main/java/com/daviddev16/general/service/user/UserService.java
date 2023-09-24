package com.daviddev16.general.service.user;

import com.daviddev16.general.model.User;
import com.daviddev16.general.payload.UserCreateRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserCreateRequest user);

    List<User> getAllUsers();

}
