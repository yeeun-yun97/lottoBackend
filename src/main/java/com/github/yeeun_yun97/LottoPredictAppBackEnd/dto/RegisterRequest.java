package com.github.yeeun_yun97.LottoPredictAppBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import static com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant.*;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude
public class RegisterRequest {
    @Email(message = DATA_EMAIL_IS_NOT_EMAIL)
    private final String email;
    @Size(min=DATA_PASSWORD_REQUIRED_LENGTH, message=DATA_PASSWORD_IS_NOT_ENOUGH_LENGTH)
    private final String password;
    @Size(min=DATA_NAME_REQUIRED_LENGTH, message=DATA_NAME_IS_NOT_ENOUGH_LENGTH)
    private final String name;
}
