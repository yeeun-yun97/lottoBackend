package com.github.yeeun_yun97.LottoPredictAppBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.entity.UserRound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonInclude
public class ReadSaveResponse {
    private int round_num, year;
    private List<OneSaveResponse> content;


    public static ReadSaveResponse of(UserRound userRound) {

        List<OneSaveResponse> content= new ArrayList<>();
        List<List<Integer>> numberList=userRound.getNumberList();
        List<List<EScore>> scoreList=userRound.getScoreList();
        for(int i=0; i<userRound.getSaves().size(); i++){
            OneSaveResponse oneSaveDto = new OneSaveResponse(numberList.get(i),scoreList.get(i));
            content.add(oneSaveDto);
        }

        return ReadSaveResponse.builder().round_num(userRound.getRound().getRoundNum())
                .year(userRound.getRound().getEndDate().getYear())
                .content(content).build();
    }


}
