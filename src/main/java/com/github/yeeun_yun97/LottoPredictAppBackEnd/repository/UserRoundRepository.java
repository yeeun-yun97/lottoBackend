package com.github.yeeun_yun97.LottoPredictAppBackEnd.repository;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.Round;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.User;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.UserRound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoundRepository extends JpaRepository<UserRound,Long> {
    Optional<UserRound> findByUserAndRound(User user, Round round);
    Page<UserRound> findAllByUser(User user, Pageable sortedByRound);
}
