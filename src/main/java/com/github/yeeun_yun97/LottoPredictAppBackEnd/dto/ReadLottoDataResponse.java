package com.github.yeeun_yun97.LottoPredictAppBackEnd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ReadLottoDataResponse {
    private Long totSellamnt,firstWinamnt, firstAccumamnt;
    private boolean returnValue;
    private String drwNoDate;
    private int drwtNo1, drwtNo2, drwtNo3, drwtNo4, drwtNo5, drwtNo6, bnusNo;
    private int firstPrzwnerCo, drwNo;
}
