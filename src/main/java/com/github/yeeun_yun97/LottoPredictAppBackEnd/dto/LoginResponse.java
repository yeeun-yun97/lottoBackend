package com.github.yeeun_yun97.LottoPredictAppBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Setter
@Getter
@JsonInclude
public class LoginResponse {
    private Long user_id;
    private String token;
}
