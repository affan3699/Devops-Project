package com.example.chatbot.exception;

import com.example.chatbot.dto.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // For your custom business exceptions
    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseModel<Object>> handleApiException(ApiException e) {

        return ResponseEntity.ok(
                ResponseModel.error(
                        e.getRespcode(),
                        e.getRespname(),
                        e.getRespdesc()
                )
        );
    }

    // For all other unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel<Object>> handleGeneralException(Exception e) {

        return ResponseEntity.ok(
                ResponseModel.error(
                        "96",
                        "SYSTEM_ERROR",
                        "Something went wrong: " + e.getMessage()
                )
        );
    }
}