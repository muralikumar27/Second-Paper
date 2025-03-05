package com.murali.secondpaper.exception;

import com.murali.secondpaper.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ResourceBundle;

@ControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<ApiResponse> exceptionHandler(Exception e) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(null, "Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
