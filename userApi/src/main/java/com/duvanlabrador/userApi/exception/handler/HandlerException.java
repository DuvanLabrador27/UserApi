package com.duvanlabrador.userApi.exception.handler;

import com.duvanlabrador.userApi.exception.ResourceNotFoundException;
import com.duvanlabrador.userApi.model.dto.ErrorDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetail> handlerResourceNotFoundException(ResourceNotFoundException exception){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDateTime(new Date());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource not found");
        errorDetail.setDetail(exception.getClass().getName());
        errorDetail.setDeveloperMessage(exception.getMessage());
        return new ResponseEntity<>(errorDetail,null,HttpStatus.NOT_FOUND);
    }




}
