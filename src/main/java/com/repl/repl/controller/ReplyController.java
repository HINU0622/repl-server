package com.repl.repl.controller;

import com.repl.repl.dto.Reply;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reply")
public class ReplyController {

    @PostMapping("/reply")
    public ResponseEntity<Reply> reply() {

    }

}
