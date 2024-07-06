package com.example.Insurance_Aggregator_Project.Advise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<String>> ApiException(ApiException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<String>(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> generalException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<String>("unexpected error !!!"));
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<String>> badCredentialsException(BadCredentialsException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<String>("Unauthorized"));
    }

    // Server Validation Exception
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ArrayList<ErrorResponse>> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        ArrayList<ErrorResponse> errorResponses = new ArrayList<>();

        for (FieldError fieldError : e.getFieldErrors()) {
            ErrorResponse errorResponse = new ErrorResponse(fieldError.getObjectName(), fieldError.getDefaultMessage(), fieldError.getField(), fieldError.getCode());
            errorResponses.add(errorResponse);
        }

        return ResponseEntity.status(400).body(errorResponses);
    }
}
