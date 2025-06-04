package com.ranpo.ranpobackend.global.auth.jwt.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "jwt.source", havingValue = "header")
@Component
public class HeaderJwtResolver implements JwtResolver {

    @Override
    public String resolve(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
