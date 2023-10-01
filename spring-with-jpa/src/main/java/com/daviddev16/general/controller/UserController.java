package com.daviddev16.general.controller;

import com.daviddev16.general.model.User;
import com.daviddev16.general.payload.UserCreateRequest;
import com.daviddev16.general.service.UserService;
import com.daviddev16.general.response.UserGenericResponse;
import jakarta.validation.constraints.Email;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/v1/users" )
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> listOfAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<?> findUserById(@PathVariable("userId") Long userId) {

        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty())
            return new ResponseEntity<>("Não foi possível localizar um " +
                    "usuário com esse id.", HttpStatus.NOT_FOUND);

        UserGenericResponse userResponse = new UserGenericResponse();
        BeanUtils.copyProperties(optionalUser.get(), userResponse);

        return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findUserByEmail(@Email @PathVariable("email") String email) {

        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isEmpty())
            return new ResponseEntity<>("Não foi possível localizar um " +
                    "usuário com esse email.", HttpStatus.NOT_FOUND);

        UserGenericResponse userResponse = new UserGenericResponse();
        BeanUtils.copyProperties(optionalUser.get(), userResponse);

        return new ResponseEntity<>(userResponse, HttpStatus.FOUND);

    }

    @DeleteMapping("/id/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {

        userService.deleteUser(userId);
        return new ResponseEntity<>("Usuário apagado.", HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<UserGenericResponse> createUser(@RequestBody UserCreateRequest createRequest) {

        User createdUser = userService.createUser(User.of(createRequest));
        UserGenericResponse userResponse = new UserGenericResponse();
        BeanUtils.copyProperties(createdUser, userResponse);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

    }

}
