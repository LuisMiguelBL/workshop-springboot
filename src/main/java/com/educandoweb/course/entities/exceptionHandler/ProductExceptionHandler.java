package com.educandoweb.course.entities.exceptionHandler;

import com.educandoweb.course.entities.error.ErrorEntity;
import com.educandoweb.course.entities.exceptions.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ProductExceptionHandler {


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorEntity> responseEntity
            (ProductNotFoundException exception,
             HttpServletRequest httpServletRequest){
        ErrorEntity errorEntity = new ErrorEntity(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Product not found by ID",
                exception.getMessage(),
                httpServletRequest.getRequestURI()
        );
        return ResponseEntity.status(404).body(errorEntity);
    }

}
