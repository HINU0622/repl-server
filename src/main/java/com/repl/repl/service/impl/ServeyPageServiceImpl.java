package com.repl.repl.service.impl;

import com.repl.repl.service.ServeyPageService;
import org.springframework.stereotype.Service;

@Service("serveyPageService")
public class ServeyPageServiceImpl implements ServeyPageService {

    @Override
    public String loadServeyPage(String user_id, String uuid) {
        return "이재진";
    }
}
