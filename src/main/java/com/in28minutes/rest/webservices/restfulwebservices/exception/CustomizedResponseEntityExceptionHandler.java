package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
    return new ResponseEntity<>(createErrorDetails(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleNotFoundExceptions(Exception ex, WebRequest request) {
    return new ResponseEntity<>(createErrorDetails(ex, request), HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    return new ResponseEntity<>(createErrorDetails(ex, request), HttpStatus.BAD_REQUEST);
  }

  private ErrorDetails createErrorDetails(Exception ex, WebRequest request) {
    return new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
  }

  private ErrorDetails createErrorDetails(MethodArgumentNotValidException ex, WebRequest request) {
    String message = String.format("Total errors: %s, Details: %s",
        ex.getErrorCount(),
        ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(" | ")));
    return new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));
  }

}
