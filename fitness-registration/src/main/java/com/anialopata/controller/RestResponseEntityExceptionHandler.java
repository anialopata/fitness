package com.anialopata.controller;

import com.anialopata.exception.FitnessException;
import com.anialopata.exception.TokenException;
import com.anialopata.model.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<RestResponse> handleException (DateTimeException ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(FitnessException.class)
    public ResponseEntity<RestResponse> handleFitnessException (FitnessException ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<RestResponse> handleTokenException (HttpServletRequest httpServletRequest, Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestResponse(HttpStatus.INTERNAL_SERVER_ERROR, "xccc", HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}