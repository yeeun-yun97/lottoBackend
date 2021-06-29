package com.github.yeeun_yun97.LottoPredictAppBackEnd.repository;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
