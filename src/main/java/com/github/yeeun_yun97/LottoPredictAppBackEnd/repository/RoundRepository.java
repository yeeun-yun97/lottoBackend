package com.github.yeeun_yun97.LottoPredictAppBackEnd.repository;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round,Long> {

    Round findFirstByFirstNotOrderByRoundNumDesc(int first);
    Round findFirstByOrderByRoundNumDesc();

}
