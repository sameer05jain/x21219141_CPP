package com.demo.demo.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler {
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> handleNoHandlerFoundException() {
        return new ResponseEntity<>(new ApiError("NOT_FOUND", "no such url found!"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException() {
        return new ResponseEntity<>(new ApiError("USER_NOT_FOUND", "no such user exists!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ApiError> handleAccountNotFoundException() {
        return new ResponseEntity<>(new ApiError("ACCOUNT_NOT_FOUND", "no such account present!"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ApiError("VALIDATION_ERROR", ex.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = new String("");
        for(ConstraintViolation x : ex.getConstraintViolations()){
            if(errorMessage.isBlank()){
                errorMessage += x.getMessage();
            }else{
                errorMessage += (", " + x.getMessage());
            }
        }
        return new ResponseEntity<>(new ApiError("VALIDATION_ERROR", errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreayExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreayExistsException ex){
        return new ResponseEntity<>(new ApiError("VALIDATION_ERROR", "User with the same email already exists"), HttpStatus.BAD_REQUEST);
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ApiError> handleException() {
    //     return new ResponseEntity<>(new ApiError("ERROR", "unable to complete request! Please try again later."), HttpStatus.BAD_REQUEST);
    // }

}
