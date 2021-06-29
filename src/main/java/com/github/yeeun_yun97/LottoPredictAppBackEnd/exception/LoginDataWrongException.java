package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant;

public class LoginDataWrongException extends SimpleException{
    public LoginDataWrongException() {
        super(Constant.EExceptionMessage.LoginDataWrongException.getMessage());
    }
}
