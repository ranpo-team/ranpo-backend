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

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    // Authorization 헤더용
    public static final String BEARER_PREFIX = "Bearer ";
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

    /**
     * JWT 토큰 생성 (Bearer prefix 없이 반환)
     */
    public String createToken(Long userId, String email, String nickname, MemberRole memberRole) {
        Date now = new Date();

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("email", email)
                .claim("memberRole", memberRole)
                .claim("nickname", nickname)
                .setExpiration(new Date(now.getTime() + TOKEN_TIME))
                .setIssuedAt(now)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    /**
     * Authorization 헤더에서 Bearer 토큰만 추출 (쿠키 기반 인증에선 사용하지 않음)
     */
    public String extractTokenFromBearer(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        throw CustomException.from(ErrorCode.INVALID_TOKEN_FORMAT);
    }

    /**
     * JWT Claims 추출 (쿠키 또는 Bearer 헤더에서 추출한 토큰 모두에 사용 가능)
     */
    public Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException | IllegalArgumentException e) {
            throw CustomException.from(ErrorCode.INVALID_TOKEN_FORMAT);
        } catch (SecurityException e) {
            throw CustomException.from(ErrorCode.INVALID_TOKEN_SIGNATURE);
        } catch (ExpiredJwtException e) {
            throw CustomException.from(ErrorCode.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw CustomException.from(ErrorCode.INVALID_TOKEN);
        }
    }
}
