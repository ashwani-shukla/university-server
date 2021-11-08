package com.university.server.exception;

import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * The type Custom exception handler.
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle resource not found exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse
                .ErrorResponseBuilder()
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                           @NonNull HttpHeaders headers,
                                                                           @NonNull HttpStatus status, WebRequest request) {
    String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> String.format("%s- %s", x.getField(), x.getDefaultMessage()))
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse
                .ErrorResponseBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed: " + errors)
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle constraint violation exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex, WebRequest request) {

        String errors = ex.getConstraintViolations()
                .stream()
                .map(x -> String.format("%s- %s", x.getPropertyPath(), x.getMessage()))
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse
                .ErrorResponseBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed: " + errors)
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle all exceptions response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse
                .ErrorResponseBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(new Date())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
