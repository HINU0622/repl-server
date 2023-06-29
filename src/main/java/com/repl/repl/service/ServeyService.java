package com.repl.repl.service;

import com.repl.repl.dto.JwtToken;
import com.repl.repl.dto.Servey;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public interface ServeyService {

    public Servey createServey(Servey servey, String token);

}
