package org.pack.manager.api.exception.handler;

import org.pack.manager.api.exception.ExceptionResponse;
import org.pack.manager.api.exception.NoPackageHistoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoPackageHistoryExceptionHandler {

    @ExceptionHandler(NoPackageHistoryException.class)
    public ResponseEntity<ExceptionResponse> handle(NoPackageHistoryException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NO_CONTENT);
    }
}
