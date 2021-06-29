package com.github.yeeun_yun97.LottoPredictAppBackEnd.service;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.*;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.SimpleException;
import org.springframework.data.domain.Page;

public interface UserService {
    RegisterResponse register(String email, String password, String name) throws SimpleException;

    SimpleOkResponse updateSaveToHistory(Long predict_id, Long user_id) throws SimpleException;

    Page<ReadSaveResponse> readSaveHistory(int page_size, int page_num, Long user_id) throws SimpleException;

    ReadUserInfoResponse readUserInfo(Long user_id) throws SimpleException;
}
