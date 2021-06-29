package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant;

public class UserCannotAccessOthersException extends SimpleException{
    public UserCannotAccessOthersException() {
        super(Constant.EExceptionMessage.UserCannotAccessOthersException.getMessage());
    }
}
