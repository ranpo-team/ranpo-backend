package com.ranpo.ranpobackend.global.auth.jwt;

import com.ranpo.ranpobackend.global.auth.dto.CurrentUser;
import com.ranpo.ranpobackend.global.exception.CustomException;
import com.ranpo.ranpobackend.member.domain.enums.MemberRole;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = jwtProvider.substringToken(authHeader);

            try {
                Claims claims = jwtProvider.extractClaims(token);

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    setAuthentication(claims);
                }
            } catch (CustomException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(Claims claims) {
        Long id = Long.parseLong(claims.getSubject());
        String email = claims.get("email", String.class);
        String nickname = claims.get("nickname", String.class);
        MemberRole memberRole = MemberRole.valueOf(claims.get("memberRole", String.class));

        CurrentUser currentUser = new CurrentUser(id, email, nickname, memberRole);
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(currentUser);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
