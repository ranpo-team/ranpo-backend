package com.ranpo.ranpobackend.member.domain.enums;

public enum ProviderType {
    GOOGLE, KAKAO, NAVER;

    public static ProviderType from(String registrationId) {
        try {
            return ProviderType.valueOf(registrationId.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("지원하지 않는 소셜 로그인 타입입니다: " + registrationId);
        }
    }
}
