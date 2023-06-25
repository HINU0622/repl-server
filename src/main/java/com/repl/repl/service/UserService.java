package com.repl.repl.service;

import com.repl.repl.dto.User;
import com.repl.repl.dto.response.SignInResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User create(User user);

    SignInResponse signIn(User user);

    User findById(String user_id);

}
