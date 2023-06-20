package com.repl.repl.controller;

import com.repl.repl.dto.User;
import com.repl.repl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ResponseEntity<User> signin(HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestBody User user) {

        User found = userService.findUser(user);

        logger.info("Controller : 쿠키 생성");
        Cookie cookie = new Cookie("userid", user.getId());
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(30*60);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(found);

    }

}
