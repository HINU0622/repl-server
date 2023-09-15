package com.repl.repl.service.impl;

import com.repl.repl.encryption.SHA256;
import com.repl.repl.dto.User;
import com.repl.repl.dto.response.SignInResponse;
import com.repl.repl.entity.UserEntity;
import com.repl.repl.jwt.JwtTokenProvider;
import com.repl.repl.repository.UserRepository;
import com.repl.repl.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RequiredArgsConstructor
@Service(value = "userService")
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public User create(User user) {

        log.info("Service : {}", user);

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

        String token = jwtTokenProvider.generateToken(
                found.get().getId(),
                new SimpleGrantedAuthority("USER"),
                30L * 60L * 1000);

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
                String token = jwtTokenProvider.generateToken(
                        entity.getId(),
                        new SimpleGrantedAuthority("USER"),
                        30L * 60L * 1000);

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

        log.info("Name  : {}", cookie.getName());
        log.info("Value : {}", cookie.getValue());

        Optional<UserEntity> userEntity = userRepository.findById(cookie.getName());
        if(userEntity.isEmpty()) return false;

        boolean is = userEntity.get().getPassword().equals(cookie.getValue());
        log.info("isValid : {}", is);

        return is;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public User getUserById(String id) {
        Optional<UserEntity> found = userRepository.findById(id);

        if(found.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "유저를 찾을 수 없습니다.");
        }

        return found.get().toDTO();
    }
}
