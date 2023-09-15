package com.repl.repl.controller;

import com.repl.repl.dto.User;
import com.repl.repl.dto.response.SignInResponse;
import com.repl.repl.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signup(HttpServletRequest request,
                                       @RequestBody User user) {

        log.info("Controller : {}", user);

        User saved = userService.create(user);
        return ResponseEntity.ok(saved);

    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signin(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 @RequestBody User user) throws NoSuchAlgorithmException {

        Cookie[] cookies = request.getCookies();

//        logger.info("Cookies : {}", cookies[0]);

        SignInResponse cookieRes = userService.signIn(cookies);

        if(cookieRes != null) {
            return ResponseEntity.ok(cookieRes);
        }

        SignInResponse signInResponse = userService.signIn(user);

        Cookie cookie = userService.makeCookie(user.getId(), user.getPassword());
        response.addCookie(cookie);

        return ResponseEntity.ok(signInResponse);

    }

}
