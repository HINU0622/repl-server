package com.repl.repl.controller;

import com.repl.repl.dto.Survey;
import com.repl.repl.jwt.ReplUserDetails;
import com.repl.repl.service.SurveyService;
import com.repl.repl.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Survey")
@Slf4j
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey Survey,
                                               Authentication authentication) {
        log.info("authentication {}", authentication);

        String user_id = ((ReplUserDetails)authentication.getPrincipal()).getUser().getId();
        Survey.setUser_id(user_id);

        Survey saved = surveyService.createSurvey(Survey);

        return ResponseEntity.ok(saved);
//        return null;
    }

}
