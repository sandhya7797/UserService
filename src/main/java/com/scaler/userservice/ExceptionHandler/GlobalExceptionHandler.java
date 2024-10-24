package com.scaler.userservice.ExceptionHandler;


import com.scaler.userservice.Exceptions.TokenNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value= TokenNotExistsException.class)
    public ResponseEntity<ExceptionDTO> handleTokenNotExistsException(TokenNotExistsException ex) {
        ExceptionDTO exceptionDTO1 = new ExceptionDTO();
        exceptionDTO1.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDTO1,HttpStatus.NOT_FOUND);
    }
}
