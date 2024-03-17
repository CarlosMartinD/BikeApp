package com.bkool.rest;

import exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Void> handleMyException(RuntimeException ex) {
        log.error("ERROR", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Void> handleMyException(AuthenticationException ex) {
        log.error("ERROR", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}
