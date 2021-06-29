package com.github.yeeun_yun97.LottoPredictAppBackEnd.exception;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.constant.Constant;

public class PythonModelLoadFailedException extends SimpleException{
    public PythonModelLoadFailedException() {
        super(Constant.EExceptionMessage.PythonModelLoadFailedException.getMessage());
    }
}
