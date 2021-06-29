package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant;

public class UserTokenExpiredException extends SimpleException {
    public UserTokenExpiredException() {
        super(Constant.EExceptionMessage.UserTokenExpiredException.getMessage());
    }
}
