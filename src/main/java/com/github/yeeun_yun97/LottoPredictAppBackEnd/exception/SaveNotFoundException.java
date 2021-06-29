package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant;

public class SaveNotFoundException extends SimpleException{
    public SaveNotFoundException() {
        super(Constant.EExceptionMessage.SaveNotFoundException.getMessage());
    }
}
