package com.github.yeeun_yun97.LottoPredictAppBackEnd.service;

import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.LottoPredictResponse;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.dto.ReadLottoRecentRoundResponse;
import com.github.yeeun_yun97.LottoPredictAppBackEnd.exception.SimpleException;
import org.springframework.stereotype.Service;

@Service
public interface LottoService {
    LottoPredictResponse getPredictNum(Long user_id) throws SimpleException;

    ReadLottoRecentRoundResponse readRecentLottoRound() throws SimpleException;
}
