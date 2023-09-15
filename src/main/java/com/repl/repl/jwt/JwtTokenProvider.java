package com.repl.repl.jwt;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component(value = "jwtTokenProvider")
public class JwtTokenProvider {
    private String secretKey = "dnrhddltks0803_eornalfowoeks_thdtkdgns";

    private final long ACCESS_TOKEN_LIFESPAN = 30L * 60L * 1000L;

    private final long REFRESH_TOKEN_LIFESPAN = 30L * 24L * 60L * 60L * 1000L;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateToken(String user_id, SimpleGrantedAuthority role, long expireMilliSeconds) {

        return Jwts.builder()
                .setSubject(user_id)
                .claim("auth", role.getAuthority())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(System.currentTimeMillis() + expireMilliSeconds))
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
        } catch (ExpiredJwtException e) {
        } catch (UnsupportedJwtException e) {
        } catch (IllegalArgumentException e) {
        }
        return false;
    }

    public void addTokenCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(JwtAuthFilter.COOKIE_NAME, token);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cookie);
    }

    public void clearTokenCooke(HttpServletResponse response) {
        Cookie cookie = new Cookie(JwtAuthFilter.COOKIE_NAME, "");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }
}
