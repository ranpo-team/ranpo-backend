package com.ranpo.ranpobackend.global.auth.oauth2.handler;

import com.ranpo.ranpobackend.global.auth.jwt.JwtProvider;
import com.ranpo.ranpobackend.global.auth.oauth2.CustomOAuth2User;
import com.ranpo.ranpobackend.member.domain.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"accessToken\": \"" + token + "\"}");
        response.sendRedirect("http://localhost:3000/oauth2/redirect");
    }
}
