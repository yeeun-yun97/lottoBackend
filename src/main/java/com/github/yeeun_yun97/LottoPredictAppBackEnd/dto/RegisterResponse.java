package com.github.yeeun_yun97.LottoPredictAppBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude
public class RegisterResponse {
    private String name;
    private String message;
}
