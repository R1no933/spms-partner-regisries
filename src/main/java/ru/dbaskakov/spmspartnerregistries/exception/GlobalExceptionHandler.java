package ru.dbaskakov.spmspartnerregistries.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class, IOException.class})
    public ResponseEntity<ApiError> handleBadRequestException(Exception e) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Error with data or reading file: " + e.getMessage(),
                UUID.randomUUID().toString()
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception e) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Inner server error. Send ticket with ID: " + UUID.randomUUID(),
                UUID.randomUUID().toString()

        );

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
