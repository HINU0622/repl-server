package com.repl.repl.service.impl;

import com.repl.repl.dto.Survey;
import com.repl.repl.entity.SurveyEntity;
import com.repl.repl.jwt.JwtTokenProvider;
import com.repl.repl.repository.SurveyRepository;
import com.repl.repl.repository.UserRepository;
import com.repl.repl.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    private final JwtTokenProvider tokenProvider;

    @Override
    public Survey createSurvey(Survey Survey) {

        SurveyEntity SurveyEntity = Survey.toEntity();
        SurveyEntity.setSurvey_id(createUUID());

        SurveyEntity saved = surveyRepository.save(SurveyEntity);

        return saved.toDTO();

    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }



}
