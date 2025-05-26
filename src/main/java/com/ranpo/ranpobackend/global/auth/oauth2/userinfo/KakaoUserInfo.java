package com.ranpo.ranpobackend.global.auth.oauth2.userinfo;

import com.ranpo.ranpobackend.member.domain.enums.ProviderType;

import java.util.Map;

public class KakaoUserInfo extends OAuth2UserInfo {

    public KakaoUserInfo(Map<String, Object> attributes) {
        super(ProviderType.KAKAO, (Map<String, Object>) attributes.get("kakao_account") );
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getNickname() {
        return (String) attributes.get("nickname");
    }
}