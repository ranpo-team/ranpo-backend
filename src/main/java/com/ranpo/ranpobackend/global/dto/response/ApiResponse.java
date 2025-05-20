package com.ranpo.ranpobackend.global.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ranpo.ranpobackend.global.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private ApiError error;

    private ApiResponse(boolean success, T data, ApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    // 성공 응답
    public static <T> ResponseEntity<ApiResponse<T>> onSuccess(T data) {
        ApiResponse<T> response = new ApiResponse<>(true, data, null);
        return ResponseEntity.ok(response);
    }

    // 실패 응답
    public static <T> ResponseEntity<ApiResponse> onFailure(ErrorCode errorCode, String detail) {
        ApiError error = ApiError.of(errorCode, detail);
        ApiResponse<T> response = new ApiResponse<>(false, null, error);
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(response);
    }

    // 실패 응답 (ResponseEntity<Object>) 반환용
    public static ResponseEntity<Object> onFailureObject(ErrorCode errorCode, String detail) {
        ApiError error = ApiError.of(errorCode, detail);
        ApiResponse<Object> response = new ApiResponse<>(false, null, error);
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(response);
    }
}
