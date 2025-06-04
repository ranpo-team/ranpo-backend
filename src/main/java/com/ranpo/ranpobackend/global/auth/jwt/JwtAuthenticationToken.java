package com.ranpo.ranpobackend.global.auth.jwt;

import com.ranpo.ranpobackend.global.auth.dto.AuthenticatedUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final AuthenticatedUser authenticatedUser;

    public JwtAuthenticationToken(AuthenticatedUser authenticatedUser) {
        super(authenticatedUser.getAuthorities());
        this.authenticatedUser = authenticatedUser;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return authenticatedUser;
    }
}
