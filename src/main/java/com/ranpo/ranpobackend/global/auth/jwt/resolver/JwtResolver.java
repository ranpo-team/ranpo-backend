package com.ranpo.ranpobackend.global.auth.jwt.resolver;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtResolver {
    String resolve(HttpServletRequest request);
}
