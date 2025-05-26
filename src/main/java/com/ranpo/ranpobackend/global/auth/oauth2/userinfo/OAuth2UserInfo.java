package com.ranpo.ranpobackend.global.auth.oauth2.userinfo;

import com.ranpo.ranpobackend.member.domain.enums.ProviderType;

import java.util.Map;

public abstract class OAuth2UserInfo {

    protected ProviderType providerType;
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        this.providerType = providerType;
        this.attributes = attributes;
    }

    public ProviderType getProviderType() {
        return providerType;
    }

    abstract public String getEmail();
    abstract public String getNickname();
}
