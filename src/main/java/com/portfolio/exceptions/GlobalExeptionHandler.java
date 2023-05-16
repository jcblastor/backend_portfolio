package com.portfolio.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.portfolio.dtos.ErrorDetailDto;

@ControllerAdvice
public class GlobalExeptionHandler extends ResponseEntityExceptionHandler {

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
  
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request
    ) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError)error).getField();
      String message = error.getDefaultMessage();

      errors.put(fieldName, message);
    });
    return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
  }
}
