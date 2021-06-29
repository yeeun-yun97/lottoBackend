package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant;

public class UserEmailDuplicatedException extends SimpleException{

    public UserEmailDuplicatedException() {
        super(Constant.EExceptionMessage.UserEmailDuplicatedException.getMessage());
    }
}
