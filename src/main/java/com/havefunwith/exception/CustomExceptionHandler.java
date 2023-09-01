package com.havefunwith.exception;

import com.havefunwith.dto.ErrorResponseValidation;
import com.havefunwith.exception.exceptions.InputValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerWebExchange;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<ErrorResponseValidation> handleInputValidationException(InputValidationException ex, ServerWebExchange webExchange) {
        ErrorResponseValidation errorResponse = new ErrorResponseValidation();
        errorResponse.setErrorCode(ex.getErrorCode());
        errorResponse.setInput(ex.getInput());
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }


}
