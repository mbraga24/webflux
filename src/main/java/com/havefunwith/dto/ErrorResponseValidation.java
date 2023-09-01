package com.havefunwith.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ErrorResponseValidation {

    private int errorCode;
    private int input;
    private String message;

}
