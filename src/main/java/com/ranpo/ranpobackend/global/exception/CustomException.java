package com.ranpo.ranpobackend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String detail;
    private final HttpStatus status;

    private CustomException(ErrorCode errorCode, String detail, HttpStatus status) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detail = detail;
        this.status = status;
    }

    public static CustomException from(ErrorCode errorCode) {
        return new CustomException(errorCode, null, errorCode.getStatus());
    }

    public static CustomException of(ErrorCode errorCode, String detail) {
        return new CustomException(errorCode, detail, errorCode.getStatus());
    }
}
