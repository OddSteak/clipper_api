package org.clipper.accessdb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="user doesn't exist")
public class GenericNotFoundException extends RuntimeException {
    public GenericNotFoundException(String msg) {
        super(msg);
    }
}
