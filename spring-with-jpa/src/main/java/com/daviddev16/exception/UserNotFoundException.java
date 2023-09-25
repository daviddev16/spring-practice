package com.daviddev16.exception;

import com.daviddev16.exception.base.GenericNotFoundException;

@Deprecated
public class UserNotFoundException extends GenericNotFoundException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
