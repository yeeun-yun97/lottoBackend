package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant;

public class LottoApiFailedException extends SimpleException{

    public LottoApiFailedException() {
        super(Constant.EExceptionMessage.LottoApiFailedException.getMessage());
    }
}
