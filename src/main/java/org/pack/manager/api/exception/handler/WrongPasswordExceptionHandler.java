package org.pack.manager.api.exception.handler;

import org.pack.manager.api.exception.ExceptionResponse;
import org.pack.manager.api.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WrongPasswordExceptionHandler {

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ExceptionResponse> handleWrongPasswordException(WrongPasswordException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("Wrong root password");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
