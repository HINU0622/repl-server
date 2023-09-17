package com.repl.repl.service.impl;

import com.repl.repl.entity.SurveyEntity;
import com.repl.repl.entity.UserEntity;
import com.repl.repl.repository.SurveyRepository;
import com.repl.repl.repository.UserRepository;
import com.repl.repl.service.SurveyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("surveyPageService")
public class SurveyPageServiceImpl implements SurveyPageService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String loadSurveyPage(String user_id, String uuid) {

        Optional<UserEntity> userEntity = userRepository.findById(user_id);
        Optional<SurveyEntity> SurveyEntity = surveyRepository.findById(uuid);

        if(userEntity.isEmpty()) throw new RuntimeException("올바르지 않은 유저입니다.");
        if(SurveyEntity.isEmpty()) throw new RuntimeException("올바르지 않은 질문입니다.");

        return user_id;
    }
}
