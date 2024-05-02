package org.pack.manager.api.exception.handler;

import org.pack.manager.api.exception.ExceptionResponse;
import org.pack.manager.api.exception.NoPackagesToUpgradeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoPackagesToUpgradeExceptionHandler {

    @ExceptionHandler(NoPackagesToUpgradeException.class)
    public ResponseEntity<ExceptionResponse> handleWrongPasswordException(NoPackagesToUpgradeException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NO_CONTENT);
    }
}
