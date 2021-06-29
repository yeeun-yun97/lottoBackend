package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant;

public class LottoApiConnectionFailedException extends SimpleException{

    public LottoApiConnectionFailedException() {
        super(Constant.EExceptionMessage.LottoApiConnectionFailedException.getMessage());
    }
}
