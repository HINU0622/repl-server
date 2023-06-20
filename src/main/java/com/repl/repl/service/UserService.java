package com.repl.repl.service;

import com.repl.repl.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User create(User user);

}
