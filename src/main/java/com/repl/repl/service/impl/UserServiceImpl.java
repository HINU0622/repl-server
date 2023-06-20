package com.repl.repl.service.impl;

import com.repl.repl.dto.User;
import com.repl.repl.entity.UserEntity;
import com.repl.repl.repository.UserRepository;
import com.repl.repl.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {

        LoggerFactory.getLogger(getClass()).info("Service : {}", user);

        UserEntity userEntity = user.toEntity();
        UserEntity saved = userRepository.save(userEntity);

        if(!saved.equals(userEntity)) throw new RuntimeException("제대로 저장이 되지 않았습니다.");

        return saved.toDTO();
    }
}
