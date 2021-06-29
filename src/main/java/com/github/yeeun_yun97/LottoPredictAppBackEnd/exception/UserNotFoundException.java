package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant;

public class UserNotFoundException extends SimpleException {
    public UserNotFoundException() {
        super(Constant.EExceptionMessage.UserNotFoundException.getMessage());
    }
}
