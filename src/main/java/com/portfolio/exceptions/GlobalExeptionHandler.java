package com.portfolio.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.portfolio.dtos.ErrorDetailDto;

@ControllerAdvice
public class GlobalExeptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetailDto> handlerResourceNotFoundExeption(ResourceNotFoundException exeption, WebRequest webRequest) {
    ErrorDetailDto errorDetailDto = new ErrorDetailDto(new Date(), exeption.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<ErrorDetailDto>(errorDetailDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetailDto> handlerGloobalExeption(Exception exception, WebRequest webRequest) {
    ErrorDetailDto errorDetailDto = new ErrorDetailDto(new Date(), exception.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<ErrorDetailDto>(errorDetailDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
}
