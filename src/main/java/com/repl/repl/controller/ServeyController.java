package com.repl.repl.controller;

import com.repl.repl.dto.Servey;
import com.repl.repl.service.ServeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/servey")
public class ServeyController {

    @Autowired
    private ServeyService serveyService;

    @PostMapping("/create")
    public ResponseEntity<Servey> createServey(HttpServletRequest request,
                                               @RequestBody Servey servey) {

        Servey saved = serveyService.createServey(servey);

        return ResponseEntity.ok(saved);
    }

}