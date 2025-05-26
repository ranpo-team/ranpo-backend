package com.ranpo.ranpobackend.global.auth.oauth2.userinfo;

import com.ranpo.ranpobackend.member.domain.enums.ProviderType;

import java.util.Map;

public class NaverUserInfo extends OAuth2UserInfo {

    public NaverUserInfo(Map<String, Object> attributes) {
        super(ProviderType.NAVER, (Map<String, Object>) attributes.get("response"));
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getNickname() {
        return (String) attributes.get("name");
    }
}
