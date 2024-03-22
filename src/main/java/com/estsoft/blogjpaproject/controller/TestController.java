package com.estsoft.blogjpaproject.controller;

import com.estsoft.blogjpaproject.domain.User;
import com.estsoft.blogjpaproject.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public class TestController {
    private UserRepository userRepository;

    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/test/{email}")
    public Optional<User> userList(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }
}
