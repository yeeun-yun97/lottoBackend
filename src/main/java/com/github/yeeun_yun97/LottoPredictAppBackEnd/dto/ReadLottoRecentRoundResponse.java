package com.github.yeeun_yun97.LottoPredictAppBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.Round;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonInclude
public class ReadLottoRecentRoundResponse {
    private int round_num;
    private LocalDate endDate;
    private List<Integer> hitNumbers;
    private int bonusNumber;

    public static ReadLottoRecentRoundResponse of(Round round){
        return ReadLottoRecentRoundResponse.builder()
                .round_num(round.getRoundNum()).hitNumbers(round.getHitNumbers())
                .bonusNumber(round.getBonus()).endDate(round.getEndDate())
                .build();
    }
}
