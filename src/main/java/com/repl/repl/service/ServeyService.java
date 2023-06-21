package com.repl.repl.service;

import com.repl.repl.dto.Servey;
import org.springframework.stereotype.Service;

@Service
public interface ServeyService {

    public Servey createServey(Servey servey);

}
