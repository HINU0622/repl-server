package com.repl.repl.controller;

import com.repl.repl.dto.User;
import com.repl.repl.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> create(HttpServletRequest request,
                                           @ModelAttribute User user) {

        LoggerFactory.getLogger(getClass()).info("Controller : {}", user);

        User saved = userService.create(user);
        return ResponseEntity.ok(saved);

    }

}
