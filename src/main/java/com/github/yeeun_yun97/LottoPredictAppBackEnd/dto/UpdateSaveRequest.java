package com.github.yeeun_yun97.LottoPredictAppBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@Setter
@Getter
@JsonInclude
public class UpdateSaveRequest {
    @NotNull
    private Long predict_id,user_id;
}
