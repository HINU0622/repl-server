package com.repl.repl.jwt;

import com.repl.repl.dto.User;
import com.repl.repl.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends GenericFilterBean {
    public static final String COOKIE_NAME = "auth_token";

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest)request).getHeader("Authorization");

        if (token == null) {
            token = getTokenByCookie((HttpServletRequest)request);
        }

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String user_id = jwtTokenProvider.getUserId(token);

            User user = userService.getUserById(user_id);
            if (user != null) {
                ReplUserDetails userDetails = new ReplUserDetails(user);

                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    private String getTokenByCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_NAME)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
