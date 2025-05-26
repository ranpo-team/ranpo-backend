package com.ranpo.ranpobackend.global.auth.oauth2;

import com.ranpo.ranpobackend.auth.service.AuthService;
import com.ranpo.ranpobackend.global.auth.oauth2.userinfo.OAuth2UserInfo;
import com.ranpo.ranpobackend.global.auth.oauth2.userinfo.OAuth2UserInfoFactory;
import com.ranpo.ranpobackend.member.domain.Member;
import com.ranpo.ranpobackend.member.domain.enums.ProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

/**
 * 1. code를 전송하여 발급받은 accessToken으로 사용자 정보를 조회
 * 2. 해당 정보로 회원가입 or 로그인 진행
 */
@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AuthService authService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); // 사용자 정보 가져오기
        ProviderType providerType = extractProviderType(userRequest); // Provider 가져오기

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, oAuth2User.getAttributes());

        Member member = processOAuth2Login(userInfo);

        return new CustomOAuth2User(member, oAuth2User.getAttributes());
    }

    private Member processOAuth2Login(OAuth2UserInfo userInfo) {
        // 1. 이미 이메일 존재
        if (authService.checkIfMemberExists(userInfo.getEmail())) {
            return authService.socialLogin(userInfo);
        }

        // 2. 이메일 존재 X
        return authService.register(userInfo);
    }

    private ProviderType extractProviderType(OAuth2UserRequest userRequest) {
        String providerName = userRequest.getClientRegistration().getRegistrationId();
        return ProviderType.from(providerName);
    }
}
