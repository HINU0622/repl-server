package com.repl.repl.service;

import com.repl.repl.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User create(User user);

    User findUser(User user);
}
