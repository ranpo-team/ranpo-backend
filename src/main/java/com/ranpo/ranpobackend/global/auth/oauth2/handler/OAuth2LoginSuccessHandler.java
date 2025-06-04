package com.ranpo.ranpobackend.global.auth.oauth2.handler;

import com.ranpo.ranpobackend.global.auth.jwt.JwtProvider;
import com.ranpo.ranpobackend.global.auth.oauth2.CustomOAuth2User;
import com.ranpo.ranpobackend.global.util.CookieUtil;
import com.ranpo.ranpobackend.member.domain.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final CookieUtil cookieUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        Member member = oAuth2User.getMember();

        String token = jwtProvider.createToken(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getRole()
        );
        String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Cookie accessTokenCookie = cookieUtil.createAccessTokenCookie(encodedToken, null);
        response.addCookie(accessTokenCookie);

        response.sendRedirect("http://localhost:3000/oauth2/redirect");
    }
}
