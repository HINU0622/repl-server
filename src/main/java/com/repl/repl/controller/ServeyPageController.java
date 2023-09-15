package com.repl.repl.controller;

import com.repl.repl.service.ServeyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servey")
public class ServeyPageController {

    @Autowired
    ServeyPageService serveyPageService;

    @GetMapping("/{user_id}/{uuid}")
    public String serveyPage(@PathVariable String user_id,
                             @PathVariable String uuid,
                             Model model) {
        model.addAttribute("author", serveyPageService.loadServeyPage(user_id, uuid));
        return "servey";
    }

}
