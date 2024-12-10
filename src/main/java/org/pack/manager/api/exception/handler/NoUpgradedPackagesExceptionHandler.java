package org.pack.manager.api.exception.handler;

import org.pack.manager.api.exception.ExceptionResponse;
import org.pack.manager.api.exception.NoPackagesToUpgradeException;
import org.pack.manager.api.exception.NoUpgradedPackagesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoUpgradedPackagesExceptionHandler {

    @ExceptionHandler(NoUpgradedPackagesException.class)
    public ResponseEntity<ExceptionResponse> handleWrongPasswordException(NoUpgradedPackagesException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NO_CONTENT);
    }
}
