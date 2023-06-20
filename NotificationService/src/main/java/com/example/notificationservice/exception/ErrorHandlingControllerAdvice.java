package com.example.notificationservice.exception;


import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorMessage onConstraintValidationException(ConstraintViolationException e, WebRequest request) {
        List<String> messages = new ArrayList<String>();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            messages.add(violation.getMessage());
        }
        ErrorMessage violation = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                messages,
                request.getDescription(false));

        return violation;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorMessage onMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        List<String> messages = new ArrayList<String>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            messages.add(fieldError.getDefaultMessage());
        }
        ErrorMessage violation = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                messages,
                request.getDescription(false));

        return violation;

    }

    @ExceptionHandler(PinwayError.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(PinwayError ex, WebRequest request) {
        List<String> messages = new ArrayList<String>();
        messages.add(ex.getMessage());

        ErrorMessage message = new ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            new Date(),
            messages,
            request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        List<String> messages = new ArrayList<String>();
        messages.add(ex.getMessage());

        ErrorMessage message = new ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            messages,
            request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(JsonPatchException.class)
//    public ResponseEntity<ErrorMessage> jsonPatchExceptionHandler(JsonPatchException jsonPatchException, WebRequest request) {
//        List<String> messages = new ArrayList<String>();
//        messages.add(jsonPatchException.getMessage());
//
//        ErrorMessage message = new ErrorMessage(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                new Date(),
//                messages,
//                request.getDescription(false));
//
//        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
