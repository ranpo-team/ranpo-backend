package com.ranpo.ranpobackend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 공통 : COM
    BAD_REQUEST("COM_400", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    UNAUTHORIZED("COM_401", HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    NOT_FOUND_URI("COM_404", HttpStatus.NOT_FOUND, "요청하신 경로를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED("COM_405", HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR("COM_500", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부에 오류가 발생했습니다."),

    // 사용자 : USER
    USER_NOT_FOUND("USER_001", HttpStatus.NOT_FOUND, "해당 사용자를 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS("USER_002", HttpStatus.CONFLICT, "이미 존재하는 이메일입니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;

    ErrorCode(String code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
