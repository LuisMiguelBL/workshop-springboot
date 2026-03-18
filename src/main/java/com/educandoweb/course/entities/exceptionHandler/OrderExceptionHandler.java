package com.educandoweb.course.entities.exceptionHandler;

import com.educandoweb.course.entities.error.ErrorEntity;
import com.educandoweb.course.entities.exceptions.OrderNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class OrderExceptionHandler  {

  @ExceptionHandler(OrderNotFoundException.class)
  public ResponseEntity<ErrorEntity> errorResponseEntity(
          OrderNotFoundException exception, HttpServletRequest httpServletRequest
  ){
    ErrorEntity error = new ErrorEntity(Instant.now(),
            404,
            "Id não encontrado",
            exception.getMessage(),
            httpServletRequest.getRequestURI());

    return ResponseEntity.status(404).body(error);
  }

}
