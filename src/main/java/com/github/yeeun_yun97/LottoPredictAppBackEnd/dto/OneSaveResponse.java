package com.github.yeeun_yun97.LottoPredictAppBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude
@AllArgsConstructor
@NoArgsConstructor
public class OneSaveResponse{
    private List<Integer> numberList;
    private List<EScore> scoreList;
}
