package com.technocorp.ericpinto.rentms.controller.exception;

import com.technocorp.ericpinto.rentms.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError standartError = StandartError.builder()
                .message(e.getMessage()).build();
        return ResponseEntity.status(status).body(standartError);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<StandartError> statusException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .headers(HttpHeaders.EMPTY)
                .body(StandartError.builder()
                        .message(e.getMessage())
                        .build());
    }
}
