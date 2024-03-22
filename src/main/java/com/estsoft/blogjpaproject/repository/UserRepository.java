package com.estsoft.blogjpaproject.repository;

import com.estsoft.blogjpaproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // email로 사용자 정보를 가져옴
    List<User> findAllByEmail(String email);
}
