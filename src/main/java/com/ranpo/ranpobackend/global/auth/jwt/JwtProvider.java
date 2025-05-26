package com.ranpo.ranpobackend.global.auth.jwt;

import com.ranpo.ranpobackend.global.exception.CustomException;
import com.ranpo.ranpobackend.global.exception.ErrorCode;
import com.ranpo.ranpobackend.member.domain.enums.MemberRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    private static final String BEARER_PREFIX = "Bearer ";
    private static final long TOKEN_TIME = 60 * 60 * 1000L; // 60분

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String createToken(Long userId, String email, String nickname, MemberRole memberRole) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(String.valueOf(userId))
                        .claim("email", email)
                        .claim("memberRole", memberRole)
                        .claim("nickname", nickname)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .setIssuedAt(date) // 발급일
                        .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                        .compact();
    }

    public String substringToken(String tokenValue) {
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            return tokenValue.substring(BEARER_PREFIX.length());
        }
        throw CustomException.from(ErrorCode.INVALID_TOKEN_FORMAT);
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException | IllegalArgumentException e) { // 형식이 잘못되거나 빈 값
            throw CustomException.from(ErrorCode.INVALID_TOKEN_FORMAT);
        } catch (SecurityException e) { // 서명 검증 실패 (위조된 토큰)
            throw CustomException.from(ErrorCode.INVALID_TOKEN_SIGNATURE);
        } catch (ExpiredJwtException e) { // 만료된 토큰
            throw CustomException.from(ErrorCode.EXPIRED_TOKEN);
        } catch (JwtException e) { // fallback
            throw CustomException.from(ErrorCode.INVALID_TOKEN);
        }
    }
}
