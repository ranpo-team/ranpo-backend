package com.ranpo.ranpobackend.global.exception;

import com.ranpo.ranpobackend.global.dto.response.ApiError;
import com.ranpo.ranpobackend.global.dto.response.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 커스텀 예외
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException ex) {
        return ApiResponse.onFailure(ex.getErrorCode(), ex.getDetail());
    }

    // 쿼리 파라미터 값 오류
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleValidationParameterError(ConstraintViolationException ex) {
        String message = "쿼리 파라미터 값이 올바르지 않습니다.";
        return ApiResponse.onFailureObject(ErrorCode.BAD_REQUEST, message);
    }

    // 필수 쿼리 파라미터 누락
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String message = "필수 파라미터가 누락되었습니다: " + ex.getParameterName();
        return ApiResponse.onFailureObject(ErrorCode.BAD_REQUEST, message);
    }

    // RequestBody 필드 유효성 검증 실패
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        String message = "유효성 검증에 실패했습니다: " + detail;
        return ApiResponse.onFailureObject(ErrorCode.BAD_REQUEST, message);
    }

    // 존재하지 않는 요청 경로
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String message = "요청 경로를 찾을 수 없습니다: " + ex.getRequestURL();
        return ApiResponse.onFailureObject(ErrorCode.NOT_FOUND_URI, message);
    }

    // 지원하지 않는 HTTP 메서드
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String message = "지원하지 않는 HTTP 메서드입니다: " + ex.getMethod();
        return ApiResponse.onFailureObject(ErrorCode.METHOD_NOT_ALLOWED, message);
    }

    // 기타 서버 내부 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpected(Exception ex) {
        log.error("[UNEXPECTED_EXCEPTION] {}", ex.getMessage(), ex);
        return ApiResponse.onFailureObject(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
