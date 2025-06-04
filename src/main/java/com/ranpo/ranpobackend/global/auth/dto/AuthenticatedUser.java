package com.ranpo.ranpobackend.global.auth.dto;

import com.ranpo.ranpobackend.member.domain.enums.MemberRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class AuthenticatedUser {

    private final Long id;
    private final String email;
    private final String nickname;
    private final MemberRole memberRole;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthenticatedUser(Long id, String email, String nickname, MemberRole memberRole) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.memberRole = memberRole;
        this.authorities = List.of(new SimpleGrantedAuthority(memberRole.toRoleName()));
    }
}
