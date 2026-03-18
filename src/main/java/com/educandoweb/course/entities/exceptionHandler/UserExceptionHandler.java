package com.educandoweb.course.entities.exceptionHandler;

import com.educandoweb.course.entities.error.ErrorEntity;
import com.educandoweb.course.entities.exceptions.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorEntity> responseEntity
            (UserNotFoundException exception, HttpServletRequest httpServletRequest){

        ErrorEntity errorEntity = new ErrorEntity(Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "User not found by ID",
                exception.getMessage(),
                httpServletRequest.getRequestURI()
                );
        return  ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorEntity);

    }

}
