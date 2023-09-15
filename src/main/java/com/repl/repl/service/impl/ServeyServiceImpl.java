package com.repl.repl.service.impl;

import com.repl.repl.dto.Servey;
import com.repl.repl.entity.ServeyEntity;
import com.repl.repl.jwt.JwtTokenProvider;
import com.repl.repl.repository.ServeyRepository;
import com.repl.repl.repository.UserRepository;
import com.repl.repl.service.ServeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service("serveyService")
public class ServeyServiceImpl implements ServeyService {

    @Autowired
    private ServeyRepository serveyRepository;

    @Autowired
    private UserRepository userRepository;

    private final JwtTokenProvider tokenProvider;

    @Override
    public Servey createServey(Servey servey) {

        ServeyEntity serveyEntity = servey.toEntity();
        serveyEntity.setServey_id(createUUID());

        ServeyEntity saved = serveyRepository.save(serveyEntity);

        return saved.toDTO();

    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }



}
