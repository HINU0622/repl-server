package com.repl.repl.controller;

import com.repl.repl.dto.Servey;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/servey")
public class ServeyController {

    @PostMapping("/create")
    public ResponseEntity<Servey> createServey() {

        Servey servey = new Servey();

        return ResponseEntity.ok(servey);
    }

}
