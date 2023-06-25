package com.repl.repl.service.impl;

import com.repl.repl.dto.User;
import com.repl.repl.dto.response.SignInResponse;
import com.repl.repl.entity.UserEntity;
import com.repl.repl.jwt.JwtTokenProvider;
import com.repl.repl.repository.UserRepository;
import com.repl.repl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User create(User user) {

        logger.info("Service : {}", user);

        UserEntity userEntity = user.toEntity();
        UserEntity saved = userRepository.save(userEntity);

        if(!saved.equals(userEntity)) throw new RuntimeException("제대로 저장이 되지 않았습니다.");

        return saved.toDTO();
    }

    @Override
    public User findById(String user_id) {

        Optional<UserEntity> found = userRepository.findById(user_id);

        if(found.isEmpty()) throw new RuntimeException("유저를 찾을 수 없음.");

        return found.get().toDTO();

    }

    @Override
    public SignInResponse signIn(User user) {

        UserEntity userEntity = user.toEntity();

        Optional<UserEntity> found = userRepository.findById(userEntity.getId());

        if(found.isEmpty()) throw new RuntimeException("유저를 찾을 수 없음.");
        if(!found.get().equals(userEntity)) throw new RuntimeException("아이디 혹은 비밀번호 불일치.");

        String token = jwtTokenProvider.makeJwtToken(found.get());

        return new SignInResponse(token);

    }

}
