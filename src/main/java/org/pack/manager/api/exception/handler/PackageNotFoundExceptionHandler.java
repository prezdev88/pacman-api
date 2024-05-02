package org.pack.manager.api.exception.handler;

import org.pack.manager.api.exception.ExceptionResponse;
import org.pack.manager.api.exception.PackageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PackageNotFoundExceptionHandler {

    @ExceptionHandler(PackageNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePackageNotFoundException(PackageNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
