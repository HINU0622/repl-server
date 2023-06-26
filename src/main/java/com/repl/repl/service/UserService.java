package com.repl.repl.service;

import com.repl.repl.dto.User;
import com.repl.repl.dto.response.SignInResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.security.NoSuchAlgorithmException;

@Service
public interface UserService {

    User create(User user);

    SignInResponse signIn(User user);

    SignInResponse signIn(Cookie[] cookies);

    User findById(String user_id);

    Cookie makeCookie(String user_id, String password) throws NoSuchAlgorithmException;

    boolean isValidCookie(Cookie cookie);

}
