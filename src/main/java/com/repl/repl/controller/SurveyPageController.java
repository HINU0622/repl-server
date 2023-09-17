package com.repl.repl.controller;

import com.repl.repl.service.SurveyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Survey")
public class SurveyPageController {

    @Autowired
    SurveyPageService surveyPageService;

    @GetMapping("/{user_id}/{uuid}")
    public String SurveyPage(@PathVariable String user_id,
                             @PathVariable String uuid,
                             Model model) {
        model.addAttribute("author", surveyPageService.loadSurveyPage(user_id, uuid));
        return "/Survey";
    }

}
