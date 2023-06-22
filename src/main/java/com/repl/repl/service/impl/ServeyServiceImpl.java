package com.repl.repl.service.impl;

import com.repl.repl.dto.Servey;
import com.repl.repl.entity.ServeyEntity;
import com.repl.repl.repository.ServeyRepository;
import com.repl.repl.service.ServeyService;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Service("serveyService")
public class ServeyServiceImpl implements ServeyService {

    @Autowired
    private ServeyRepository serveyRepository;

    @Override
    public Servey createServey(Servey servey) {

        ServeyEntity serveyEntity = servey.toEntity();
        serveyEntity.setServey_id(createUUID());

        ServeyEntity saved = serveyRepository.save(serveyEntity);

        if(!saved.equals(serveyEntity)) throw new RuntimeException("설문이 제대로 생성되지 않음.");

        return saved.toDTO();

    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

}
