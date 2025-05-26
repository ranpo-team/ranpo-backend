package com.ranpo.ranpobackend.member.service;

import com.ranpo.ranpobackend.global.exception.CustomException;
import com.ranpo.ranpobackend.global.exception.ErrorCode;
import com.ranpo.ranpobackend.member.domain.Member;
import com.ranpo.ranpobackend.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> CustomException.from(ErrorCode.USER_NOT_FOUND));
    }
}
