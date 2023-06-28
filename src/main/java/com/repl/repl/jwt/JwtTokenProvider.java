package com.repl.repl.jwt;

import com.repl.repl.dto.AuthUser;
import com.repl.repl.entity.UserEntity;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    private final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    public String makeJwtToken(UserEntity user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public AuthUser getUserDtoOf(String authorizationHeader) {
        validationAuthorizationHeader(authorizationHeader);

        String token = extractToken(authorizationHeader);
        Claims claims = parsingToken(token);

        return new AuthUser(claims);
    }

    private Claims parsingToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private void validationAuthorizationHeader(String header) {
        if (header == null || !header.startsWith(jwtProperties.getTokenPrefix())) {
            throw new IllegalArgumentException("Invalid token!");
        }
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(jwtProperties.getTokenPrefix().length());
    }

    private Claims getClaims(String token) {
        try{
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
            // 토큰 유효성 확인
        } catch (SecurityException e) {
            log.info("Invalid JWT signature.");
            throw new RuntimeException("토큰의 서명이 잘못되었습니다.");
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            throw new RuntimeException("유효하지 않은 구성의 토큰입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            throw new RuntimeException("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            throw new RuntimeException("지원되지 않는 형식이거나 구성의 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            throw new RuntimeException("잘못된 토큰입니다.");
        }
    }
    public String getUserEmailFromToken(String token) {
        return (String) getClaims(token).get("email");
    }

}
