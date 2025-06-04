package com.ranpo.ranpobackend.global.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {

    public static final int ACCESS_TOKEN_EXPIRY = 60 * 60; // 1시간

    @Value("${cookie.secure}")
    private boolean cookieSecure;

    public Cookie createAccessTokenCookie(String token, String domain) {
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(cookieSecure); // 운영 환경에 따라
        cookie.setPath("/");
        cookie.setMaxAge(ACCESS_TOKEN_EXPIRY);
        if (domain != null) cookie.setDomain(domain);
        return cookie;
    }

    public static void expireCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}