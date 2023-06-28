package com.repl.repl.controller;

import com.repl.repl.dto.User;
import com.repl.repl.dto.response.SignInResponse;
import com.repl.repl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/sign-up")
    public ResponseEntity<User> signup(HttpServletRequest request,
                                           @RequestBody User user) {

        logger.info("Controller : {}", user);

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
