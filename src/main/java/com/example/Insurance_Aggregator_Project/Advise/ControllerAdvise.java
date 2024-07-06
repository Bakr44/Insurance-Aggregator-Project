package com.example.Insurance_Aggregator_Project.Advise;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<String>> ApiException(ApiException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage()));
    }

    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<ApiResponse<String>> NoSuchFileException(NoSuchFileException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>("File path is invalid."));
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse<String>> IOException(IOException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>("I/O Exception occurred."));
    }


    // Server Validation Exception
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ArrayList<ErrorResponse>> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ArrayList<ErrorResponse> errorResponses = new ArrayList<>();

        for (FieldError fieldError : e.getFieldErrors()) {
            ErrorResponse errorResponse = new ErrorResponse(fieldError.getObjectName(), fieldError.getDefaultMessage(), fieldError.getField(), fieldError.getCode());
            errorResponses.add(errorResponse);
        }

        return ResponseEntity.status(400).body(errorResponses);
    }

    // Server Validation Exception
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> ConstraintViolationException(ConstraintViolationException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }


    // SQL Constraint Ex:(Duplicate) Exception
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponse> SQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    // wrong write SQL in @column Exception
    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<ApiResponse> InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(200).body(new ApiResponse(msg));
    }

    // Database Constraint Exception
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> DataIntegrityViolationException(DataIntegrityViolationException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    // Method not allowed Exception
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    // Json parse Exception
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }

    // TypesMisMatch Exception
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(new ApiResponse(msg));
    }
}
