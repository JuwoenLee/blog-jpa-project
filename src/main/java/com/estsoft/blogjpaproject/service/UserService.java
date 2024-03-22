package com.estsoft.blogjpaproject.service;

import com.estsoft.blogjpaproject.domain.User;
import com.estsoft.blogjpaproject.dto.AddUserRequest;
import com.estsoft.blogjpaproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final 변수의 생성자
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public User save(AddUserRequest request) {
        return userRepository.save(
                new User(request.getEmail(), encoder.encode(request.getPassword()))
        );
    }
}