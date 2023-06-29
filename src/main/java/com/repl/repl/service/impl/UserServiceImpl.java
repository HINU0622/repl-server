package com.repl.repl.service.impl;

import com.repl.repl.encryption.SHA256;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
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
        try {
            userEntity.setPassword(SHA256.encrypt(userEntity.getPassword()));
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        UserEntity saved = userRepository.save(userEntity);

        if(!saved.equals(userEntity)) throw new RuntimeException("제대로 저장이 되지 않았습니다.");

        return saved.toDTO();
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

    @Override
    public SignInResponse signIn(Cookie[] cookies) {

        if(cookies == null) {
            return null;
        }


        for(Cookie c : cookies) {
            if(isValidCookie(c)) {
                UserEntity entity = new UserEntity(c.getName(), c.getValue());
                String token = jwtTokenProvider.makeJwtToken(entity);

                return new SignInResponse(token);
            }
        }
        return null;
    }

    public Cookie makeCookie(String user_id, String password) throws NoSuchAlgorithmException {

        Cookie cookie = new Cookie(user_id, password);
        cookie.setDomain("10.80.162.99");
        cookie.setPath("/");
        cookie.setMaxAge(30*60);
        cookie.setSecure(true);

        return cookie;
    }

    @Override
    public boolean isValidCookie(Cookie cookie) {

        logger.info("Name  : {}", cookie.getName());
        logger.info("Value : {}", cookie.getValue());

        Optional<UserEntity> userEntity = userRepository.findById(cookie.getName());
        if(userEntity.isEmpty()) return false;

        boolean is = userEntity.get().getPassword().equals(cookie.getValue());
        logger.info("isValid : {}", is);

        return is;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
