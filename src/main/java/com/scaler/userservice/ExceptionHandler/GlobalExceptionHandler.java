package com.scaler.userservice.ExceptionHandler;


import com.scaler.userservice.Exceptions.EmailNotExistsException;
import com.scaler.userservice.Exceptions.EmailNotVerifiedException;
import com.scaler.userservice.Exceptions.TokenIsInvalidOrNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value= TokenIsInvalidOrNotExistsException.class)
    public ResponseEntity<ExceptionDTO> handleTokenNotExistsException(TokenIsInvalidOrNotExistsException ex) {
        ExceptionDTO exceptionDTO1 = new ExceptionDTO();
        exceptionDTO1.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDTO1,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmailNotVerifiedException.class)
    public ResponseEntity<ExceptionDTO> handleEmailNotVerifiedException(EmailNotVerifiedException ex) {
        ExceptionDTO exceptionDTO1 = new ExceptionDTO();
        exceptionDTO1.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDTO1,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmailNotExistsException.class)
    public ResponseEntity<ExceptionDTO> handleEmailNotVerifiedException(EmailNotExistsException ex) {
        ExceptionDTO exceptionDTO1 = new ExceptionDTO();
        exceptionDTO1.setMessage(ex.getMessage());
        return new ResponseEntity<>(exceptionDTO1,HttpStatus.NOT_FOUND);
    }



}
