package com.repl.repl.service;

import com.repl.repl.dto.Survey;
import org.springframework.stereotype.Service;

@Service
public interface SurveyService {

    public Survey createSurvey(Survey Survey);

}
