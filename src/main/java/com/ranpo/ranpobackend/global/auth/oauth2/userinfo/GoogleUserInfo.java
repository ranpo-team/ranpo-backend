package com.ranpo.ranpobackend.global.auth.oauth2.userinfo;

import com.ranpo.ranpobackend.member.domain.enums.ProviderType;

import java.util.Map;

public class GoogleUserInfo extends OAuth2UserInfo {

    public GoogleUserInfo(Map<String, Object> attributes) {
        super(ProviderType.GOOGLE, attributes);
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
