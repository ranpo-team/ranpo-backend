package com.ranpo.ranpobackend.global.auth.jwt;

import com.ranpo.ranpobackend.global.auth.dto.CurrentUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final CurrentUser currentUser;

    public JwtAuthenticationToken(CurrentUser currentUser) {
        super(currentUser.getAuthorities());
        this.currentUser = currentUser;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return currentUser;
    }
}
