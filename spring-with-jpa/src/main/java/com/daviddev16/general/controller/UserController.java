package com.daviddev16.general.controller;

import com.daviddev16.general.model.User;
import com.daviddev16.general.payload.UserCreateRequest;
import com.daviddev16.general.service.user.UserService;
import com.daviddev16.general.view.UserGenericResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserGenericResponse> createUser(@RequestBody UserCreateRequest createRequest) {
        User createdUser = userService.createUser(createRequest);
        UserGenericResponse userResponse = new UserGenericResponse();
        BeanUtils.copyProperties(createdUser, userResponse);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }


    public UserService getUserService() {
        return userService;
    }
}
