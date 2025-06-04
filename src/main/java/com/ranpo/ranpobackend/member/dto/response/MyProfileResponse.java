package com.ranpo.ranpobackend.member.dto.response;

import com.ranpo.ranpobackend.global.auth.dto.AuthenticatedUser;
import com.ranpo.ranpobackend.member.domain.Member;
import lombok.Getter;

@Getter
public class MyProfileResponse {

    private final Long id;
    private final String email;
    private final String nickname;

    private MyProfileResponse(Long id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public static MyProfileResponse from(AuthenticatedUser user) {
        return new MyProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }
}
