package com.repl.repl.controller;

import com.repl.repl.dto.AuthUser;
import com.repl.repl.dto.Servey;
import com.repl.repl.service.ServeyService;
import com.repl.repl.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/servey")
@Slf4j
public class ServeyController {

    @Autowired
    private ServeyService serveyService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Servey> createServey(HttpServletRequest request,
                                               @RequestBody Servey servey,
                                               Authentication authentication) {
        log.info("authentication {}", ((AuthUser) authentication.getPrincipal()).getId());

        String user_id = ((AuthUser) authentication.getPrincipal()).getId();
        servey.setUser_id(user_id);

        Servey saved = serveyService.createServey(servey);

        return ResponseEntity.ok(saved);
//        return null;
    }

}
