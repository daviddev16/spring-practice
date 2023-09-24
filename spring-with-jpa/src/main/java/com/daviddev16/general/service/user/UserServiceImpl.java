package com.daviddev16.general.service.user;

import com.daviddev16.exception.EmailAlreadyExistsException;
import com.daviddev16.general.model.User;
import com.daviddev16.general.payload.UserCreateRequest;
import com.daviddev16.general.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserCreateRequest createRequest) {

        if (getUserRepository().findByEmail(createRequest.getEmail()).isPresent())
            throw new EmailAlreadyExistsException("Email já cadastrado");

        User newUserFromRequest = new User(createRequest);
        return getUserRepository().save(newUserFromRequest);

    }

    @Override
    public List<User> getAllUsers() {
        return getUserRepository().findAll();
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }
}
