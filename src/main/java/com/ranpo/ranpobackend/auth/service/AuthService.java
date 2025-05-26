package com.ranpo.ranpobackend.auth.service;

import com.ranpo.ranpobackend.global.auth.oauth2.userinfo.OAuth2UserInfo;
import com.ranpo.ranpobackend.global.exception.CustomException;
import com.ranpo.ranpobackend.global.exception.ErrorCode;
import com.ranpo.ranpobackend.global.util.IdGenerator;
import com.ranpo.ranpobackend.member.domain.Member;
import com.ranpo.ranpobackend.member.domain.enums.MemberRole;
import com.ranpo.ranpobackend.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public boolean checkIfMemberExists(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional
    public Member socialLogin(OAuth2UserInfo userInfo) {
        Member member = getMemberByEmail(userInfo.getEmail());

        // DB와 현재 요청한 Provider와 일치하다면 로그인 처리
        if (member.getProviderType().equals(userInfo.getProviderType())) {
            return member;
        }

        // 로그인 실패, 다른 Provider로 로그인 유도
        throw new OAuth2AuthenticationException(
                new OAuth2Error(
                        ErrorCode.PROVIDER_TYPE_MISMATCH.getCode(),
                        ErrorCode.PROVIDER_TYPE_MISMATCH.getMessage()
                                + " " + member.getProviderType().name() + "로 로그인해주세요.",
                        null
                )
        );
    }

    @Transactional
    public Member register(OAuth2UserInfo userInfo) {
        Member member = Member.builder()
                        .email(userInfo.getEmail())
                        .nickname(userInfo.getNickname())
                        .uid(IdGenerator.generateUuidUid())
                        .providerType(userInfo.getProviderType())
                        .role(MemberRole.USER)
                        .build();
        return memberRepository.save(member);
    }

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> CustomException.from(ErrorCode.USER_NOT_FOUND));
    }
}
