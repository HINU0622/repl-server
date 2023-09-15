package com.repl.repl.service;

import com.repl.repl.dto.User;
import com.repl.repl.dto.response.SignInResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public interface UserService {

    User create(User user);

    SignInResponse signIn(User user);

    SignInResponse signIn(Cookie[] cookies);

    Cookie makeCookie(String user_id, String password) throws NoSuchAlgorithmException;

    boolean isValidCookie(Cookie cookie);

    String resolveToken(HttpServletRequest request);

    User getUserById(String id);

}
