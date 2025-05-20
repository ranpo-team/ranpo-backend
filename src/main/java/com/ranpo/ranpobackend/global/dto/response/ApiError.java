package com.ranpo.ranpobackend.global.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ranpo.ranpobackend.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ApiError {

    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String detail;

    public ApiError(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public static ApiError of(ErrorCode errorCode, String detail) {
        return new ApiError(errorCode.getCode(), errorCode.getMessage(), detail);
    }
}
