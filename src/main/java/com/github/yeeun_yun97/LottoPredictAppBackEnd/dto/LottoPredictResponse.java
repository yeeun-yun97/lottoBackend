package com.github.yeeun_yun97.LottoPredictAppBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.Save;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.UserRound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude
public class LottoPredictResponse {
    private Long predict_id, round_id;
    private List<Integer> numberList;

    public static LottoPredictResponse of(Save save) {
        return LottoPredictResponse.builder().predict_id(save.getId())
                .round_id(save.getUserRound().getRound().getId())
                .numberList(save.getNumberList()).build();
    }
}
