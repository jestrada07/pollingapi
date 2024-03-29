package com.polling.api.jaimipollingapi.handler;

import com.polling.api.jaimipollingapi.errors.ErrorDetail;
import com.polling.api.jaimipollingapi.errors.ValidationError;
import com.polling.api.jaimipollingapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex,  HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Message not readable :(");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());
        return (new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND));
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve,HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ErrorDetail errorDetail= new ErrorDetail();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
//
//        errorDetail.setTitle("Validation failed :(");
//        errorDetail.setDetail(manve.getMessage());
//        errorDetail.setDeveloperMessage(manve.getClass().getName());
//
//        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
//        for (FieldError fe : fieldErrors) {
//            List<ValidationError> validationErrors = errorDetail.getErrors().get(fe.getField());
//            if(validationErrors == null){
//                validationErrors = new ArrayList<>();
//                errorDetail.getErrors().put(fe.getField(), validationErrors);
//            }
//
//            ValidationError validationError = new ValidationError();
//            validationError.setCode(fe.getCode());
//            validationError.setMessage(this.messageSource.getMessage(fe, null));
//            validationErrors.add(validationError);
//        }
//        return  (super.handleExceptionInternal(manve, errorDetail, headers, status, request));
//    }
@Override
public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorDetail errorDetail= new ErrorDetail();
    errorDetail.setTimeStamp(new Date().getTime());
    errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
    String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri", 1);
    if(requestPath == null){
        requestPath = request.getHeader("Header test");
    }
    errorDetail.setTitle("Validation failed :(");
    errorDetail.setDetail("Input validation failed :(");
    errorDetail.setDeveloperMessage(manve.getClass().getName());
    List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
    for (FieldError fe : fieldErrors) {
        List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
        if (validationErrorList == null){
            validationErrorList = new ArrayList<ValidationError>();
            errorDetail.getErrors().put(fe.getField(), validationErrorList);
        }
        ValidationError validationError = new ValidationError();
        validationError.setCode(fe.getCode());
        validationError.setMessage(fe.getDefaultMessage());
        //errorDetail.getErrors().put(fe.getField(), validationErrorList);
        validationErrorList.add(validationError);
    }
    return handleExceptionInternal(manve, errorDetail, headers, status, request);
}



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException httpMessageNotReadableException, HttpHeaders headers, HttpStatus status, WebRequest request){
      ErrorDetail errorDetail = new ErrorDetail();
      errorDetail.setTimeStamp(new Date().getTime());
      errorDetail.setStatus(status.value());
      errorDetail.setTitle("Message Not readable");
      errorDetail.setDetail(httpMessageNotReadableException.getMessage());
      errorDetail.setDeveloperMessage(httpMessageNotReadableException.getClass().getName());

        return(super.handleExceptionInternal(httpMessageNotReadableException,errorDetail,headers,status,request));


    }





}
