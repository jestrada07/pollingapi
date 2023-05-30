package com.polling.api.jaimipollingapi.handler;

import com.polling.api.jaimipollingapi.exception.ResourceNotFoundException;
import com.polling.api.jaimipollingapi.errors.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
            ErrorDetail errorDetail = new ErrorDetail();
            errorDetail.setTimeStamp(new Date().getTime());
            errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
            errorDetail.setTitle("Resource not found big dawg");
            String errorMessage = ex.getMessage();
            errorDetail.setDetail(errorMessage);
            errorDetail.setDeveloperMessage("stinky pete you");
            return new ResponseEntity<>(errorMessage + errorDetail, HttpStatus.NOT_FOUND);
        }




}
