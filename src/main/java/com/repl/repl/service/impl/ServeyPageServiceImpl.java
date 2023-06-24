package com.repl.repl.service.impl;

import com.repl.repl.entity.ServeyEntity;
import com.repl.repl.entity.UserEntity;
import com.repl.repl.repository.ServeyRepository;
import com.repl.repl.repository.UserRepository;
import com.repl.repl.service.ServeyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("serveyPageService")
public class ServeyPageServiceImpl implements ServeyPageService {

    @Autowired
    private ServeyRepository serveyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String loadServeyPage(String user_id, String uuid) {

        Optional<UserEntity> userEntity = userRepository.findById(user_id);
        Optional<ServeyEntity> serveyEntity = serveyRepository.findById(uuid);

        if(userEntity.isEmpty()) throw new RuntimeException("올바르지 않은 유저입니다.");
        if(serveyEntity.isEmpty()) throw new RuntimeException("올바르지 않은 질문입니다.");

        return user_id;
    }
}
